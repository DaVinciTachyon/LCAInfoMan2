## Social Graph

This application interrogates the GitHub API to build visualisation of data available that elucidates some aspect of the software engineering process, such as a social graph of developers and projects, or a visualisation of individual of team performance.

In order to run the application:

1. Register your new application on Github : https://github.com/settings/applications/new. In the "callback URL" field, enter "http://localhost:8080/oauth/redirect". Once you register, you will get a client ID and client secret.
2. Replace the values of the `clientID` and `clientSecret` variables in the [index.js](/index.js) file 
3. Replace the value of clientID in index.html.
3. Install dependencies by executing: `npm install` or `yarn`.
4. Start the server by executing `npm start`
5. Navigate to http://localhost:8080 on your browser.