# Contributing to AgentCraft

Thanks for helping improve AgentCraft. This repository is a fork of
blendi-remade/agentcraft and is licensed under the MIT License.

## Before you start

Check the existing issues first. For a new feature, open an issue describing
the idea before implementing it, so the change can be discussed. Bug fixes
with a clear reproduction can go straight to a pull request.

## Development setup

AgentCraft has three areas:

- `foreman/` — the Node and TypeScript service. Run `npm ci` there, then
  `npm test`, `npm run typecheck` and `npm run check`. The last command also
  verifies that the generated protocol documentation is up to date.
- `mod/` — the Fabric mod for Minecraft. Run `gradlew.bat build` on Windows or
  `./gradlew build` on Unix from that directory. `mod/DEV.md` documents the
  toolchain, the entrypoints and the source-set layout.
- `tools/` — development and recording scripts. Run `npm ci` there before
  using them.

## Pull requests

- Keep each pull request to a single coherent change.
- Describe what changes and how you verified it, including the exact commands
  you ran.
- The checks in `foreman/` must pass without warnings or suppressions.
- Do not include generated build output, local configuration or credentials.

## Commit style

Use short, imperative subjects in the form `type: description`, where `type`
is one of `feat`, `fix`, `docs`, `refactor`, `test` or `chore`.
