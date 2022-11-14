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

RecyclerView Adapter
Maven Central API License

This library is designed to help separate the creation and binding of views being adapted by a RecyclerView Adapter, from the Adapter itself.

Instead of writing an Adapter that needs to know which layout resource(s) to inflate for the items, as well as binding logic and click listeners, this library allows you to write a ViewModel class which wraps your data model, defining how the view should be created, bound, how many spans it should take up, etc. You no longer need to write a RecyclerView Adapter class at all.

List<ViewModel> items = new ArrayList<>();

    var textview: TextView
    var date: TextView
    var img: ImageView
    var img_cnt: TextView

    // list view data set
    if (view_TypeOne == Constant.LISTCOUNTONE) {
        textview = itemView.findViewById(R.id.title_tv)
        textview.text = modelItem!!.title

        img = itemView.findViewById(R.id.imag_v)
        Picasso.with(context).load(modelItem.img).into(img);

        date = itemView.findViewById(R.id.date_tvv)
        date.text = dateTime()
    }

The adapter supports diffing between different sets of ViewModels (via the ContentsComparator interface). Diff results are automatically calculated (on the background thread) and supplied to the ViewModelAdapter, which performs the appropriate remove, move & insert RecyclerView animations.

SpanSizeLookup can be applied to a GridLayoutManager, meaning the span size can be defined in the ViewModel.

Gradle
implementation "androidx.recyclerview:recyclerview:1.2.1"
implementation "androidx.recyclerview:recyclerview-selection:1.1.0"

Java packages structure
We recommend using a feature based package structure for your code. This has the following benefits:

Clearer feature dependency and interface boundaries.
Promotes encapsulation.
Easier to understand the components that define the feature.
Reduces risk of unknowingly modifying unrelated or shared code.
Simpler navigation: most related classes will be in the one package.
Easier to remove a feature.
Simplifies the transition to module based build structure (better build times and Instant Apps support)
The alternative approach of defining your packages by how a feature is built (by placing related Activities, Fragments, Adapters etc in separate packages) can lead to a fragmented code base with less implementation flexibility. Most importantly, it hinders your ability to comprehend your code base in terms of its primary role: to provide features for your app.