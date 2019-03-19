# unfold

Just a basic idea of the implementation
It lacks:
* fullscreen since I don't quite understand how should it look like
* proper video preview
* video layout measurement
* state management
* video recycling while scrolling
* caching
* persisting data
* memory optimisations
* no research was done for a better video player and media chooser


# Android Test

Write an application that has one screen with a fullscreen horizontal collection view. There should be 2 types of cells - one that displays an image, another should show a video. Number of cells should be dynamic - there should be a "plus" button to add an empty cell.

When user taps on the cell there should be a way to select an image or a video from the camera roll, after media was selected it should be displayed in the cell.

When video is imported it should autoplay in the cell.

Language - Kotlin
Android 5.1 and up
You can use 3rd party dependencies for selecting images/videos from camera roll.
