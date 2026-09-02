# Voluneer — Android Implementation

Android Studio implementation of the assigned Figma "Volunteer App" design
(prompts: Login, Profile, Home). Package: `com.example.volunteerapp`.

The three layouts (`activity_login.xml`, `activity_home.xml`,
`activity_profile.xml`) are built to match the exported Figma screens
pixel-for-pixel as closely as XML allows: same copy, same numbers (142
Hours / 2.4K Impacted / 7 Causes / 12w Streak, 71% / 142 / 200 hrs impact
ring, Hunger Relief 68% / Environment 45% / Education 30% / Housing 22%),
same icon choices (heart-in-circle logo, open-eye password toggle, share
icon on the profile cover).

The project's setup (folder layout, version catalog, Gradle/AGP versions,
naming conventions) is deliberately mirrored on the reference
`Nit3213SandBoxApp` project provided for the unit, so it should look and
feel familiar to open alongside it.

## How to open

1. Unzip and open the `VolunteerApp` folder in Android Studio.
2. Let Gradle sync — it will download AndroidX, Material Components, and
   Glide from Google's/Maven Central's repositories. An internet connection
   is required for the first sync.
3. **Target device: Android 17 ("Cinnamon Bun") / API 37.** The project is
   configured with `compileSdk = 37` and `targetSdk = 37` to match a
   **Pixel 10a (arm64), Android 17.0 ("Cinnamon Bun")** virtual device in
   Device Manager. `minSdk = 27` so it also runs on somewhat older emulators
   if needed. If Android Studio prompts that the API 37 SDK platform isn't
   installed, install it via *Tools ▸ SDK Manager ▸ SDK Platforms ▸ Android
   Cinnamon Bun (API 37)*, then let Gradle re-sync.
4. This project uses **Gradle 9.4.1** and **AGP 9.2.1** (see
   `gradle/wrapper/gradle-wrapper.properties` and
   `gradle/libs.versions.toml`), which is the minimum AGP line that supports
   compiling against API 37. AGP 9's built-in Kotlin compiler support means
   no separate `org.jetbrains.kotlin.android` plugin is applied - Kotlin
   sources under `src/main/java` are picked up automatically.
5. The app needs the `INTERNET` permission (already declared) because photos
   are loaded live from Unsplash via Glide, matching the photography used in
   the Figma prompts.

## Project structure

Matches the reference project's conventions:

- Kotlin source files live under `app/src/main/java/...` (Android Studio's
  standard source folder name, even for a 100% Kotlin project - there is no
  `src/main/kotlin` folder).
- The three Activities live in a `ui` sub-package:
  `com.example.volunteerapp.ui.{LoginActivity, HomeActivity, ProfileActivity}`.
- View IDs use `snake_case` (e.g. `join_button`, `hero_image`,
  `sign_up_card_1_button`), matching the reference project's
  `navigate_second_screen_button` / `greeting_text` style rather than
  camelCase.
- Dependencies are declared through a Gradle version catalog
  (`gradle/libs.versions.toml`) and referenced in both `build.gradle.kts`
  files as `alias(libs.plugins...)` / `implementation(libs...)`, the same
  pattern the reference project uses.

## Screens → Activities

| Screen (assigned prompt) | Activity | Layout |
|---|---|---|
| Login | `ui/LoginActivity.kt` | `activity_login.xml` |
| Home / dashboard | `ui/HomeActivity.kt` | `activity_home.xml` |
| Profile | `ui/ProfileActivity.kt` | `activity_profile.xml` |

Navigation: Login → Home ("Join the Movement") → Profile (bottom nav or
avatar) → back to Home (back arrow or bottom nav "Home").

## Coding style

Also mirrors the reference project's style:

- Views are declared as `private lateinit var` fields, assigned once via
  `findViewById` in `onCreate()`.
- Click listeners are attached in `onResume()`, not `onCreate()`.
- Navigation `Intent`s are declared as `private val ... by lazy { Intent(...) }`
  properties rather than being constructed inline at click-time.

## Kept deliberately simple

This is an intro-course assignment, so the code favours plain, explicit
Android over more advanced patterns:

- **No RecyclerView / Adapter / ViewHolder / model classes.** The two
  opportunity cards on Home and the three history rows and four stat tiles
  on Profile are all written out directly as XML (`activity_home.xml` /
  `activity_profile.xml`), with `android:text` set right on each `TextView`.
  Each Activity just calls `findViewById` and wires up a click listener —
  no adapter boilerplate, no generics, no `ViewHolder` pattern.
- **No shared/reusable `<include>` for cards.** Each screen's bottom
  navigation bar is its own small layout file (`layout_bottom_nav_home.xml`,
  `layout_bottom_nav_profile.xml`) with the *current* tab already styled
  active, rather than one shared layout toggled at runtime — simpler to
  read even though it repeats a little XML.
- **No ViewBinding, no ConstraintLayout.** Just `findViewById`,
  `LinearLayout`/`FrameLayout`, and plain Kotlin — the same toolkit an intro
  Android unit typically covers in its first few weeks.
- **Cause-breakdown bars** on Profile are four small hardcoded
  `LinearLayout`s using `layout_weight` to size a coloured "filled" segment
  against the total width — no custom `View`, canvas drawing, or chart
  library.

## Self-taught concepts (beyond lecture material)

- **Vector drawables** (`res/drawable/ic_*.xml`): all icons (home, search,
  heart, person, pin, clock, users, chevron, eye, share, etc.) were
  hand-converted from SVG `path`/`circle` data into Android
  `<vector>`/`<path>` XML — including turning SVG `<circle>` elements
  (which vector drawables don't support directly) into two-arc `pathData`
  commands. See comments in each icon file.
  Reference: https://developer.android.com/develop/ui/views/graphics/vector-drawable-resources
- **Adaptive launcher icon** (`mipmap-anydpi/ic_launcher.xml`): a
  foreground vector layered over a solid background color, rather than a
  flat PNG.
- **Glide** (`com.github.bumptech.glide:glide`): used to asynchronously load
  and cache the Unsplash photography referenced by the design (hero image,
  avatar, opportunity card photos, history thumbnails) — not covered in
  lecture, but necessary since the layouts are photo-heavy. Usage is kept to
  the simplest possible form: `Glide.with(this).load(url).into(imageView)`.
- **Radial `<ring>` progress drawable** (`progress_ring_impact.xml`): a
  `layer-list` of two `ring` shapes used to fake the circular "71%
  complete" impact-goal ring using only core Android drawables (no chart
  library, no custom `View`/`onDraw`).

## Optional: exact webfonts

The design uses **Fraunces** (headlines) and **Nunito** (body text). To keep
the project buildable without extra downloads, the theme currently maps
these to the system `serif` / `sans-serif` families. To use the exact
fonts: download the `.ttf` files from fonts.google.com for Nunito
(400/600/700/800) and Fraunces (400/600/700), drop them into a new
`res/font/` folder, and add `font-family` XML resources referencing them
(see Android's "Fonts in XML" docs) — then point `themes.xml` / `styles.xml`
at `@font/nunito` / `@font/fraunces` instead of `sans-serif` / `serif`.

## Known simplifications

- The bottom nav's "Explore" and "Impact" tabs are visually present (per the
  design) but not wired to new screens, since only Login/Home/Profile were
  in scope for this assignment.
- "Share Profile" and the share icon are visual only (no real Android share
  sheet / `Intent.ACTION_SEND` wired up) — outside this assignment's scope.
- Opportunity/history data is hardcoded directly in the layout XML rather
  than coming from a real backend, consistent with a static two-card,
  three-history-item design.
