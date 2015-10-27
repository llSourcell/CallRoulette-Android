#CallRoulette (Android Twilio Client)
CallRoulette is a Twilio-powered Android demo app. This app was made to showcase the Twilio Android SDK how it works alongside a TwiML server. 

###Prerequisites

Android Studio - http://developer.android.com/sdk/index.html 

Latest Android SDK https://www.twilio.com/docs/client/android

Twilio account https://www.twilio.com/try-twilio

###How it works

The app uses a single button to call someone or hang up on them. When the user presses it, the app requests a capability token from a TwiML server. Once it has the token, it can connect to the Twilio server and attempt to connect the user to another person to initiate a phone call. The TwiML server creates a queue to hold any waiting callers. If no one is in the queue, the user is placed into the queue and waits until someone else calls. If someone is already in the queue, the user is connected to them and the queue is emptied. The queue never reaches a capacity greater than 1! 

The queue REST API is awesome, it allows for multiple simulatenous conversations. It's the marriage of the client, TwiML server, and Twilio cloud that makes this app possible.

###Further Links

Queue API Docs

https://www.twilio.com/docs/api/rest/queue


Accompanying TwiML Web App 

https://code.hq.twilio.com/sraval/CallRouletteWebApp

