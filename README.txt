Using the Android Client

Details
 Published: 08 October 2014
 Hits: 1755
Initial Setup
The following steps will briefly guide you through your initial configuration of your traccar app. Please note that some of the settings are not available in the original app. However, you can download the source code with the modifications from github.

The Device identifier should be auto-generated from the IMEI number of your phone. But any other id can be assigned as well. This id will be used to identify your device on the website.
The server address can point to your self-hosted traccar server or to this domain (http://total-surveillance.de/). The location updates will be send there.
The port entered here should match your configured port. If you use this site, it is the default port 5005.
The update frequency can be set to any number. Remember that a small number means a lot of updates, more traffic and a higher power consumption on your device.
All notification sms including the low battery warning are sent to the number configured here.
When your battery drained to a given percentage a notification will be sent once via sms. The percentage can be configured here. Choose a value between 0 and 100. If you either choose 0 or 100 than no sms will be sent.
The location provider determines the source of the location information. GPS is more accurate than the information gained through wifi. Optionally both providers can be enabled.
Choosing the extended format will sent more information to the traccar server. There is no reason not enabling it at this point except when your mobile traffic is really limited.
Ticking this box will sent all position updates via SMS. This can be useful when no mobile internet is available but can get be expensive without an sms flat.
The last switch will enable/disable position tracking.
The SMS commands
The client can be controlled through commands send to it via sms. Therefore settings can easily be altered without direct access to the device. Some commands work without any parameters other require some. The following commands are supported (case doesn't matter):

enable - enables sms logging
disable - disables sms logging
pos - returns the last known position
server [url] - changes the tracking server
port [port] - changes the server port
frequency [interval] - sets the interval between position updates
number - sets the number logging sms are sent to
battery - sets the level when a notification will be sent. Note that values of 100 or 0 will disable this feature

-------------------

traccar-client

Web page - http://www.traccar.org

Author - Anton Tananaev (anton.tananaev@gmail.com)

SUMMARY:

Traccar Client is Android GPS tracking application. It can work with
Traccar open source server software.

LICENSE:

Apache License, Version 2.0

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

