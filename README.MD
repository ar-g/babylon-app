I decided to go with architecture because I love good architecture which can 
increase the quality of the app and the speed of the whole team, 
I also wanted to try architecture-components for some time  

# Implementation:

I have used my common suite of libraries for rapid development.
- RxJava to simplify asynchronous operations and threading in general
- Retrofit for fast API wiring
- GSON and gson class generators for JSON
- RecyclerView as widget to build some parts of UI 

### Regarding architecture:

I'm using a mix of MVP and MVI UI patterns and Clean Architecture in production at my current project, 
but I decided to use and test here architecture components for the first time.

My thoughts on architecture components after writing this test.
On the plus side
- fast and easy to write i.e. many things are handled and are ready out of the box, such as
lifecycle, accidental leaking of activity or fragment, resubscription
- good tool for a demo app

Minuses I noticed
- LiveData and ViewModel are polluted with android dependencies, bad separation of concerns 
for example no easy separation of threading for testing
- LiveData abstraction itself takes too many responsibilities such as lifecycle, threading, pushing values
- testing is not so proficient and mature as for rxJava subjects

### Regarding dependency injection:

We have one app scope, excluding ViewModel-Activity scope, in my opinion, it is more than 
enough to have a manual app-scoped injection for such a small project.

# Duration:

~8 hours

# What further improvements or new features would be?

My first thing would be to understand better business needs, user needs, 
user auditory, internet speed, a range of devices. From there I would rank tasks by value/effort and iterate.

Some things which are on my list are:

* Ask designers for design
* More tests, including acceptance tests with Espresso
* Consider using a binary format such as protobuf etc.
* Figure out if the content of API is staticish and can be cached internally.
* Specific error handling(HTTP codes and other possible errors)
* Nullability of API is not clear
* Improve performance of UI by using custom views. 
Direct work with Layout instead of TextView, 
ViewGroup instead of LinearLayout/ConstraintLayout, 
Drawable instead of ImageView.
* Proguard rules