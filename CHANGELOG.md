# Changelog

## v3.0.0-rc3 (07/06/2018)

#### Enhancements:

- [**enhancement**][**refactoring**] Avoid direct model accesses in runtime services [#42](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/42)
- [**enhancement**][**refactoring**] Manage parallel steps in debuggers + refactoring [#39](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/39)
- [**enhancement**][**refactoring**] [Debug/Engine] Replace MSEOccurence" with "Step" when possible [#34](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/34)

#### Bug Fixes:

- [**bug**] Fix timeline dimensions arrows wrongly disabled [#54](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/54)
- [**bug**][**refactoring**] Patch mutable field extractor for pure k3 project + helper refactoring [#52](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/52)
- [**bug**] Fix nullpointer trace constructor [#28](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/28)

#### Refactorings

- [**refactoring**] Move creation of generic debugger in common execution engine code [#36](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/36)
- [**refactoring**] Move debuggers from java engine plugins to execution framework plugins [#31](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/31)

#### Version upgrades

- [**bump**] Upgrade Melange to version 2018-04-11 [#37](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/37)
- [**bump**] Upgrade Melange version to 2018-01-29 [#26](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/26)

#### Release Engineering

- [**releng**] Use Gren for release note management [#38](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/38)

---

## v3.0.0-rc2 (10/04/2018)

#### Enhancements:

- [**enhancement**] re-activate equivalency classes computing [#22](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/22)
- [**enhancement**] Add a default value that should be used for swtbot tests of gemoc [#19](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/19)

#### Version upgrades

- [**bump**] Update tycho to version 1.0.0 [#13](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/13)

---

## v3.0.0-rc1 (07/12/2017)

#### Enhancements:

- [**enhancement**] Improve xdsml test suites [#18](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/18)
- [**enhancement**] Improve gemoc project template wizard [#5](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/5)

#### Bug Fixes:

- [**bug**] Fsm example fix [#2](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/2)

#### Refactorings

- [**refactoring**] Mass rename org.gemoc -> org.eclipse.gemoc [#9](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/9)
- [**refactoring**] Rename abstract dsl launch configuration delegate ui [#8](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/8)

#### Version upgrades

- [**bump**] Upgrade k3 to latest version [#15](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/15)
- [**bump**] Migrate to Eclipse oxygen [#11](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/11)
- [**bump**] Upgrade GEMOC version to 2.4.0.qualifier [#3](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/3)

#### Release Engineering

- [**releng**] Remove old releng files with ref to Eclipse Neon [#12](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/12)
- [**releng**] Local full build support [#1](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/1)
