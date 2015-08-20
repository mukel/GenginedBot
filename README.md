# GenginedBot
Telegram Bot for Google App Engine 

Provides a simple Telegram Bot to be hosted on Google's Cloud platform.
GAE free quotas should be more than enough for a bot.

## About GAE incompatibilities
GenginedBot uses the [Scalaj-Http](https://github.com/scalaj/scalaj-http) library; due to an incompatibility with GAE, a patched version of the library is provided.
The incompatibility was already fixed and the PR merged but hasn't been published yet.

## Why GenginedBot?
By using the [TelegramBot4s](https://github.com/mukel/telegrambot4s) library it's possible to run bots almost anywhere, a local PC, a Raspberry Pi, an old Android smartphone.

GenginedBot aims to provide a REALLY SIMPLE WAY (clone and go) to create a bot on the cloud.
Gengined bots are REALLY fast, providing instant responses, all thanks to GAE speedy servers and network. GAE provides generous (free) quotas and certificates.
