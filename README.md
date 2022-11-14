# MyApplication
 Android project

Libraries :
Jackson is a Java library for JSON serialization and deserialization, it has a wide-scoped and versatile API, supporting various ways of processing JSON: streaming, in-memory tree model, and traditional JSON-POJO data binding.

Gson : is another popular choice and being a smaller library than Jackson, you might prefer it to avoid 65k methods limitation. Also, if you are using

picasso : is another option for loading and caching images. It has support for animated GIFs, circular images and claims of better performance than Picasso, but also a bigger method count.



Proguard configuration
ProGuard is normally used on Android projects to shrink and obfuscate the packaged code.

Whether you are using ProGuard or not depends on your project configuration. Usually you would configure Gradle to use ProGuard when building a release APK.

buildTypes {
debug {
minifyEnabled false
}
release {
signingConfig signingConfigs.release
minifyEnabled true
proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
}
}