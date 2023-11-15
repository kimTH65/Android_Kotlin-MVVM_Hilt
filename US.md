# Android(Kotlin)-MVVM_Hilt
 
<div align="right">
  <h5>
    Language : 
    <a href="README.md">한국어</a> 
      ,
    <a href="JP.md">日本語</a> 
  </h5>
</div>
 
 - Using MVVM Pattern,Observer Pattern<br>
 
 - Update UI in real time using Room(Local Database), LiveData, ViewModel, Observer Method<br>
 
 - Using Recyclerview, dataBinding ...<br>
 
 - Using Koin for DI(Dependency injection)
 
 - Using <a href ="https://www.kobis.or.kr/kobisopenapi/homepg/main/main.do">Korean Film Council API</a>
<br>

# Hilt

 - Dagger-based Dependency Injection Library<br>
 
 - Provides containers to all Android classes and automatically manages life cycles<br>
 
 - @HiltAndroidApp : Enables the application to generate Hilt code automatically

 - @AndroidEntryPoint : Create a container (component) to use dependency injection with Hilt

 - @Inject : In case of public accessors, dependency injection is possible with the Inject Annotation

 - @Module : For private accessors, module,Inject dependency into IntallIn

 - @IntallIn : Set which Android class (activity, fragemntetc) to use

 - @Binds : Used in Abstract class, can only have one return value (implementation), not static

 - @Provides : Used by object, can have multiple parameters, call dependency each time used

<br>
<hr>

<h3>1. Manifest</h3>

<h5>Manifest : XML with sensitive metadata for Android apps</h5>

<div align="center">
 <h5>
  <a href="app/src/main/AndroidManifest.xml">
   Manifest
  </a>
 </h5>
</div>

```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.memo">
    <uses-permission android:name="android.permission.INTERNET"/>
    <application
        android:name=".di.App"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.AppCompat.Light.NoActionBar">
        <activity
            android:name=".ui.MainActivity"

            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```

#

<h3>2. Application</h3>

<h5>ApplicationClass : Accessible from all components within the application, declaring the Koin module using the module global function</h5>

<div align="center">
 <h5>
  <a href="app/src/main/java/com/example/memo/di/App.kt">
   Application
  </a>
 </div>

```
  .
  .
  .

@HiltAndroidApp
open class App : Application()
```

#

<h3>3. DI Module</h3>

<h5>NetworkModule → DataSourceModule → RepositoryModule → UseCaseModule </h5>

<div align="center">
 <h5>
  <a href="app/src/main/java/com/example/memo/di/NetworkModule.kt">
   NetworkModule
  </a>
 </div>

```
  .
  .
  .

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    val BASE_URL: String = "https://www.kobis.or.kr"

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(provideLoggingInterceptor())
            .build()
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun provideInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory)
    : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit): RetrofitInterface {
        return retrofit.create(RetrofitInterface::class.java)
    } // RetrofitInterface -> app/src/main/java/com/example/memo/model/dto/request/RetrofitInterface.kt
}
```

<div align="center">
 <h5>
  <a href="app/src/main/java/com/example/memo/di/DataSourceModule.kt">
   DataSourceModule
  </a>
 </div>

```
  .
  .
  .

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideDataSource(retrofitInterface: RetrofitInterface): DataSource = DataSourceImpl(retrofitInterface)
} // DataSource, DataSourceImpl -> app/src/main/java/com/example/memo/model/dto
```

<div align="center">
 <h5>
  <a href="app/src/main/java/com/example/memo/di/RepositoryModule.kt">
   RepositoryModule
  </a>
 </div>

```
  .
  .
  .

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(dataSourceImpl: DataSourceImpl): Repository = RepositoryImpl(dataSourceImpl)
    
} // Repository, RepositoryImpl -> app/src/main/java/com/example/memo/repository
```

<div align="center">
 <h5>
  <a href="app/src/main/java/com/example/memo/di/UseCaseModule.kt">
   UseCaseModule
  </a>
 </div>

```
  .
  .
  .

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideUseCase(repositoryImpl: RepositoryImpl) : UseCase = UseCase(repositoryImpl)
} // UseCase -> app/src/main/java/com/example/memo/usecase/UseCase.kt
```

#

<h3>4. ViewModel</h3>

<div align="center">
 <h5>
  <a href="app/src/main/java/com/example/memo/viewmodel/MainViewModel.kt">
   ViewModel
  </a>
 </div>

```
  .
  .
  .

@HiltViewModel
class MainViewModel @Inject constructor(
        private val useCase: UseCase
    ) : ViewModel() {
    val movieRepository: LiveData<List<Dto>> get()= _movieRepository
    private val _movieRepository = MutableLiveData<List<Dto>>()
    //("0a248ab8367333fba08f7bfade19fce4","targetDt")

    fun getMovie(key: String,targetDt: String)=
        viewModelScope.launch{
            val result = useCase.getMovie(key,targetDt)

            _movieRepository.postValue(result!!)
        }
}
```

#

<h3>5. View</h3>

<div align="center">
 <h5>
  <a href="app/src/main/java/com/example/memo/ui">
   View
  </a>
 </div>

```
  .
  .
  .

@AndroidEntryPoint
class Fragment_Main : Fragment() {
    private var mBinding: FragmentMainBinding? = null
    private val binding get() = mBinding!!

    private val mainViewModel by viewModels<MainViewModel>()

    private fun init(){
        observeViewModel()
    }
    private fun observeViewModel(){
        val result = mainViewModel.getMovie("0a248ab8367333fba08f7bfade19fce4","20220215")

        val mAdapter = RecyclerViewAdapter(requireContext() , mainViewModel)
        val LinearManager = LinearLayoutManager(requireContext())
        LinearManager.reverseLayout = true
        LinearManager.stackFromEnd = true


        mainViewModel.movieRepository.observe(requireActivity(), Observer { movies ->
            // Update the cached copy of the users in the adapter.
            movies?.let { mAdapter.setMovies(it) }
        })

        binding.recyclerview.apply {
            adapter = mAdapter
            layoutManager = LinearManager
        }
    }

  .
  .
  .

```
