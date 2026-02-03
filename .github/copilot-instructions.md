# Copilot / AI Agent Quickstart for mechumbra-samples ✅

## Quick architecture summary 🔧
- Root is a small Gradle multi-project: top-level `:core` and `:desktop` modules (see `settings.gradle`, `build.gradle`).
- `projects/` contains additional standalone sample projects (e.g. `projects/minirpg`) each with their own Gradle wrappers and a similar `core`/`desktop` split.
- `core` contains game logic and rendering (libGDX ApplicationAdapter / ApplicationListener). `desktop` contains the platform launcher (main entry point: `DesktopLauncher.java`).
- Assets are stored per-app in `assets/` (root) or per-sample (e.g. `projects/minirpg/assets/`). UI uses Scene2D (see `screens/` package).

## Key files to inspect 📁
- `desktop/src/.../DesktopLauncher.java` — desktop entry and macOS note about `-XstartOnFirstThread`.
- `core/src/.../MechUmbraGdxGame.java` and `projects/minirpg/core/src/.../MechUmbraGdxRPGGame.java` — main game loops, input handling, model creation.
- `projects/minirpg/desktop/build.gradle` — example `run`, `debug`, and `dist` tasks and macOS JVM args.
- `projects/minirpg/core/src/.../screens/*` — HUD and popup widgets (Scene2D usage and `createBasicSkin()` pattern).
- `build.gradle` (root) — central versions (e.g. libGDX `gdxVersion = '1.12.1'`).

## Developer workflows / commands ▶️
- Build full root project: `./gradlew build` (from repo root).
- Run the minirpg sample (recommended):
  - cd into the sample folder: `cd projects/minirpg`
  - Run the desktop app: `./gradlew :desktop:run` (or `./gradlew run` from that folder)
  - Debug: `./gradlew :desktop:debug`
  - Create runnable jar: `./gradlew :desktop:dist` (fat jar creation defined in `desktop/build.gradle`).
- Running from IDE: open the `desktop` module and run `DesktopLauncher` main class. On macOS add `-XstartOnFirstThread` to the JVM args (also shown in `DesktopLauncher.java` and configured automatically in `projects/minirpg/desktop/build.gradle`).

## Project-specific patterns & conventions 🧭
- Procedural geometry: uses `ModelBuilder` / `MeshPartBuilder` to build primitives (see `MechUmbraGdxRPGGame` triangular pyramid example). Follow this approach when adding simple test geometry.
- Input handling: prefer `InputMultiplexer` to combine `Stage`, `CameraInputController`, and custom `InputProcessor` (see `MechUmbraGdxRPGGame#create()`).
- UI / HUD: scene2d `Stage` + `ScreenViewport`; fonts loaded with `FreeTypeFontGenerator` from `assets/fonts/Roboto-Regular.ttf` (see `MainHUDScreen`, `EnableFightPopupWidget`).
- Assets: runtime working directory for the desktop app is set to the `assets` folder in `desktop` tasks; when creating JavaExec tasks replicate `workingDir = project.assetsDir` to ensure relative paths work.
- Logging: lightweight samples use `System.out.println` extensively; new work should keep consistent style unless introducing a logging framework project-wide.

## Integration points & external deps ⚙️
- Main external dependency: libGDX (version centralized in root `build.gradle` as `gdxVersion = '1.12.1'`).
- Desktop native libraries are referenced in `:desktop` dependencies (natives for desktop/backends).
- Samples may provide their own `gradlew` wrappers (e.g. `projects/minirpg/gradlew`). Use the sample wrapper to run that sample in isolation.

## Editing guidance for common tasks ✍️
- Add a new in-game HUD metric: update `MainHUDScreen.render()` — append to the `StringBuilder` and update `labelTTF.setText(...)`.
- Add a new keyboard command: add a case to the keyboard `InputProcessor` in `MechUmbraGdxRPGGame` (or add a new `InputProcessor` and register it via `InputMultiplexer`).
- Add a runnable desktop sample: copy `projects/minirpg/desktop/build.gradle` tasks (`run`, `debug`, `dist`) and set `project.ext.mainClassName`, `project.ext.assetsDir`.

## Tests, CI, and gaps ⚠️
- There are no CI workflows or repo-level `.github/workflows` — consider adding a simple `build` workflow if needed.
- Automated tests are sparse for the samples; most validation is manual visual verification and `System.out` traces.

## Helpful notes & gotchas 💡
- Always verify which `assets/` directory the desktop working directory points to; many runtime errors come from missing assets.
- macOS: remember `-XstartOnFirstThread` when running Lwjgl3 apps (desktop launchers call this out in comments and `projects/minirpg/desktop/build.gradle` injects the arg automatically).
- When adding features, prefer small, self-contained changes with a runnable sample demonstrating the change (update a sample under `projects/` where appropriate).

---
If any part of this summary is unclear or you want the instructions to include more examples (e.g., a small PR example or a checklist for adding features), tell me which section to expand and I will iterate. ✨