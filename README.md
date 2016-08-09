# api-qa-spock-testing

##Preface
Given the concepts below, write a small test suite that exercises and verifies that the afformentioned concepts are functioning. We use [Spock](http://spockframework.github.io/spock/docs/1.0/index.html) for our testing and would prefer for this exercise that toolset by used.

You can access all of the documentation for our public API [here](https://rachio.readme.io/) and we are happy to answer any questions. We will provide you with a valid oAuth token to use for your test suite. 

We expect that this will take you somewhere around 3-4 hours to complete, so we aren’t expecting “perfect” code or a 100% complete test suite (if there is such a thing). If you find yourself stuck or spending too much time on the problem, send us what you have with an explanation of what you planned on doing if you were to spend more time on it.

Good luck!

##Public API Concepts

###Persons
`Person`s are the core of the public Rachio API. A `Person` has information about the user as well as the `Device`'s that belong to the user

###Devices
A `Device` represents the physical sprinkler controller. A `Device` has information about itself and has collections of `Zone`'s, `Schedule`'s, `Webhook`'s. 

###Zones
A `Zone` represents an area in the `Person`'s yard where sprinklers can be run. It contains information about the `Zone`.

###Schedules
A `Schedule` represents a rule that the `Person` has setup in the Rachio app to run a set of `Zone`'s. For this exercise you can just focus on ScheduleRules (not FlexScheduleRules).

###Webhooks
A `Webhook` is a way to get HTTP callbacks when things happen to a `Device`. There are different event types that are returned in the callback and represent what happened to the device.

##Extra credit
Use your own HTTP server with webhooks to verify functionality. You may want to use a tool like [ngrok](https://ngrok.m/).
