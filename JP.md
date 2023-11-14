# Android(Kotlin)-MVVM_Hilt
 
<div align="right">
  <h5>
    Language : 
    <a href="README.md">한국어</a> 
      ,
    <a href="JP.md">日本語</a> 
  </h5>
</div>
 
 - MVVM Pattern,Observer Pattern使用<br>
 
 - Live Data、View Model、Observer Methodを利用してリアルタイムでUI変更<br>
 
 - Recyclerview, dataBinding など使用<br>
 
 - DI(Dependency injection)で Koin使用
 
 - <a href ="https://www.kobis.or.kr/kobisopenapi/homepg/main/main.do">韓国の映画振興委員会API</a>を使用
<br>

# Hiltとは

 - Daggerを元に作ったDependency Injection Library<br>
 
 - すべてのAndroid ClassにContainerを提供し、Life Cycleを自動的に管理する<br>
 
 - @HiltAndroidApp : 該当ApplicationにHilt Codeを自動的に生成できるようにする

 - @AndroidEntryPoint : Hiltで依存性注入を使用するContainer（Component）を作成

 - @Inject : Publicアクセス者の場合、Inject Annotationで依存性注入が可能

 - @Module : Privateアクセス者の場合 Module,IntallInで依存性注入が可能

 - @IntallIn : どのAndroid Class(activity、fragemnt ...)を使用するか設定

 - @Binds : Abstract Class(抽象クラス)で使用、リターン値(具現体)を1つだけ持つことができる、Staticではない

 - @Provides : Objectで使用、複数のParameterを持つことができる、使用時に毎回依存性呼び出し

<br>
<hr>

<h3>1. Manifest</h3>

<h5>Manifest : Android 앱에 대한 중요한 메타 데이터가 포함된 XML</h5>

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

<h5>ApplicationClass : 어플리케이션내의 모든 컴포넌트에서 접근 가능</h5>

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
