# GitTracker

GitTracker makes it easy to track and manage crashes for your Android applications! GitTracker will upload stacktraces from crashes and upload it to Parse (requires GitTracker.API) and automatically create a GitHub issue for that crash so developers working on the same project can easily collaborate when fixing crashes.

## Usage

Using GitTracker is as simple as adding 1 line of code to your ```Application``` class.

1. Clone GitTracker.API and push it to your Parse project.
2. Create an access token for GitTracker to access your repository.
3. Add the following line of code to your ```Application``` class:
```java
GitTracker.initialize(this, GITHUB_ACCESS_KEY, GITHUB_REPO_NAME, PARSE_APP_ID, PARSE_CLIENT_ID);
```

## Sample

GitTrackerSample is an Android application in this repository that can be used for testing GitTracker. Just clone the project and add your API keys to test it. 

**NOTE:**
You must have a Parse project with GitTracker.API running before you can use GitTracker.

## License
GitTracker is released under the The GNU General Public License v3.0 (GPLv3), which can be found in the LICENSE file in the root of this project.
