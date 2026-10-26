# Build DARK HUNTER APK from your phone using GitHub Actions

This project ZIP includes a GitHub Actions workflow that builds the debug APK in GitHub's cloud. You do not need Android Studio on your phone, but you do need a GitHub account and a browser.

1. Create a repository on GitHub and set it to **Public** (or use a private repo if your account supports Actions for it).
2. On GitHub's website, open the repository and choose **Add file → Upload files**.
3. Extract this ZIP first. Upload the *contents* of the `DarkHunterAndroid` folder, including the hidden `.github` folder and its workflow file. If the phone's file picker hides `.github`, use a file manager that shows hidden files, or upload the ZIP to a computer and push the contents.
4. Commit the files to the `main` branch.
5. Open the repository's **Actions** tab. Choose **Build DARK HUNTER APK**, then **Run workflow** if it has not started automatically.
6. Wait for the run to finish with a green checkmark.
7. Open the completed run and scroll to **Artifacts**. Download `DARK-HUNTER-debug-apk`.
8. Extract the downloaded artifact ZIP; it contains `app-debug.apk`. Tap it to install. Android may ask you to allow installs from your browser or file manager.

The workflow builds a debug APK from source. It is a visual demo with local-only demo login and does not connect to or modify Free Fire. Do not add passwords, tokens, or other secrets to the repository.
