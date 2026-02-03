# mechumbra-samples

Mech Game Sample Setup Strategy — a small collection of libGDX samples demonstrating OpenGL, camera control, Scene2D UI and simple gameplay loops.

## Quickstart

- Build the entire repo (from repo root):

```bash
./gradlew build
```

- Run a sample (recommended: `minirpg`):
  - cd into the sample folder and use the sample Gradle wrapper:

```bash
cd projects/minirpg
./gradlew run       # runs the desktop app (uses project assets folder)
```

  - Alternatively, from the sample folder you can run the example tasks directly:

```bash
./gradlew :desktop:run   # run desktop module
./gradlew :desktop:debug # run with debug flag
./gradlew :desktop:dist  # create a runnable fat-jar
```

- Desktop apps rely on the `assets/` folder as their working directory — ensure the app's workingDir points to the correct `assets` folder (see `projects/minirpg/desktop/build.gradle`).

## Key files to inspect

- `desktop/src/.../DesktopLauncher.java` — desktop entry point; macOS requires the JVM arg `-XstartOnFirstThread` for LWJGL3 applications.
- `core/src/.../MechUmbraGdxGame.java` — simple 2D app example (ApplicationAdapter).
- `projects/minirpg/core/src/.../MechUmbraGdxRPGGame.java` — full example with 3D models, camera controller, input multiplexer and Scene2D UI.
- `projects/minirpg/core/src/.../screens/*` — HUD and popup widgets (`MainHUDScreen`, `EnableFightPopupWidget`, `QuitPopupWidget`).
- `projects/minirpg/core/src/.../rpg/Character.java` — small RPG character model and combat helpers.
- `build.gradle` (root) — central versions (e.g., `gdxVersion = '1.12.1'`).

## Project conventions & patterns

- Procedural geometry is created with `ModelBuilder` / `MeshPartBuilder` (see `MechUmbraGdxRPGGame` triangular pyramid example).
- Input handling commonly uses `InputMultiplexer` to combine `Stage`, `CameraInputController`, and custom `InputProcessor` implementations.
- UI / HUD use Scene2D with `Stage` + `ScreenViewport`. Fonts are loaded via `FreeTypeFontGenerator` from `assets/fonts/Roboto-Regular.ttf`.
- Simple logging uses `System.out.println` across the samples — expect console-based debugging output.

## Assets

- The project contains a top-level `assets/` folder for the root sample and per-sample `assets/` directories (e.g., `projects/minirpg/assets/`).
- Ensure runtime working directory points to the intended `assets` folder when running from Gradle or the IDE; many runtime issues come from missing assets.

## Building & Release

- Sample desktop modules provide a `dist` task that builds a runnable jar (fat jar) by bundling runtime dependencies (`projects/*/desktop/build.gradle`).

## Tests & CI

- Automated tests are minimal; most validation is manual visual verification and console traces.
- There are currently no `.github/workflows` — consider adding a simple `build` workflow to run `./gradlew build` on PRs.

## Helpful notes & gotchas

- macOS: add `-XstartOnFirstThread` to JVM args when running LWJGL3 desktop apps (this repo's sample `desktop` Gradle tasks add it automatically on macOS).
- Use the sample's Gradle wrapper (`projects/<sample>/gradlew`) when working inside that sample to avoid version mismatches.

