# MyAndroidGame
In the last three days, I completed a Android-Based game, which is similar to "Piano Tiles"(别踩白块儿). 
  
BGM is the soul of a music game, I chose 'Summer', a vivid light music. By importing 'android.media.MediaPlayer', it's easy to play music in my game.
  
What's more, to ensure the fluency of the game, I tried to use multi-Thread, which was unfamiliar to me. My strategy is "everything has a thread",so all the movements and animations of background, white blocks, as well as roses have their own thread. Frankly speaking, too many threads may be more of a hindrance than a help, but for a beginner, this structure is the easiest to handle.  

In the process, I met loads of problems. For example, I created a new thread for music playing at first, but when run the game, it only plays music without presenting the view. So I deleted the thread for music, and just start the music in the function 'OnCreate',then everything turns out to be ok. But why???? 

Finally, I'd like to show the rule of my game. If you touch a white block, you can gain 10  points, but if you miss it, you will lose one life(3 life in total). If you touch a rose, you can gain 20 points, and lose it won't cause any loss in both your point and life.
