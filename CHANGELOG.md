# Changelog

## v3.3.0 (28/07/2021)

#### Enhancements:

- [**enhancement**] Consolidation and automatic bump of K3 versions [#185](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/185)
- [**enhancement**] [Event Manager PR#4] Event manager GUI plugin [#188](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/188)
- [**enhancement**] Improve test logs about locked jobs [#193](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/193)
- [**enhancement**][**refactoring**][**releng**] Add Sirius ELK support [#184](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/184)
- [**enhancement**] [Event Manager PR#1] Support for execution step parameters + misc. [#186](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/186)
- [**enhancement**] [Event Manager PR#2] Event manager plugin [#187](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/187)

#### Bug Fixes:

- [**bug**] Disable flacky tests [#192](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/192)
- [**bug**] Fix wrongly reported .dsl error  [#180](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/180)
- [**bug**][**releng**] Fix event manager integration problems [#189](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/189)

#### Version upgrades

- [**bump**] Bump to Eclipse 2021-06 [#196](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/196)
- [**bump**] Bump eclipse 2020-12 [#183](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/183)
- [**bump**] Bump to Eclipse 2020-03 [#178](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/178)

#### Release Engineering

- [**releng**] Explicit repository feature [#190](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/190)

---

## v3.2.0 (16/06/2020)

#### Enhancements:

- [**enhancement**][**refactoring**] DslValidator modified to use languageComponents instead of RuleProvider [#175](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/175)
- [**enhancement**] IConfigurationElement arrays changed for ArrayLists in GEMOC tools [#177](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/177)
- [**enhancement**] Proposal Provider and Highlighting for dsl files in GEMOC Studio [#172](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/172)
- [**enhancement**] Fix TreeViewerHelper resize too often [#168](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/168)
- [**enhancement**] Cache sorting of addon on engine status change [#165](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/165)
- [**enhancement**] New plug-in for validation in GEMOC Studio [#164](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/164)
- [**enhancement**] Adds Addon parameter feature and makes equivalency classes computing optional [#157](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/157)
- [**enhancement**] Implement addon priority mechanism [#149](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/149)
- [**enhancement**] Add new GenericDebugModelId for generic model presentation and breakpoint management [#141](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/141)
- [**enhancement**] Add information about number of completed steps in the EngineStatus class [#143](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/143)
- [**bug**][**enhancement**] Reduce Sirius refresh notification [#125](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/125)
- [**enhancement**][**refactoring**] Improve default Engine addon launch conf tab [#136](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/136)
- [**enhancement**] Behavioral interface DSL, event occurrence metamodel and value metamodel [#119](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/119)
- [**enhancement**] Add "openFileFromPlugin" method to TestUtil [#109](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/109)
- [**enhancement**] Clarifies Eclipse consoles used by the engines [#116](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/116)

#### Bug Fixes:

- [**bug**] Avoid NPE in IntrospectiveMutableFieldExtractor for MM with null names [#150](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/150)
- [**bug**] Remove Logical Step View from perspective extension declaration [#145](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/145)
- [**bug**] Fix xdsmlFilePath attribute in plugin.xml [#139](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/139)
- [**bug**] Handle Sirius forced resourceSet [#140](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/140)
- [**bug**] Fix double instantiation of language specific addons [#121](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/121)

#### Refactorings

- [**refactoring**] Kermeta3 validation rule moved into gemoc-debugging from the K3 validation plugin [#170](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/170)
- [**refactoring**] Move commons for gemoc-studio to  gemoc-studio-modeldebugging git repository [#148](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/148)
- [**refactoring**] Move JavaEngine to its own git repository [#147](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/147)
- [**refactoring**] Use general extension point + nature and  builder refactoring [#133](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/133)

#### Version upgrades

- [**bump**] Bump to Sirius 6.1.3 [#138](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/138)
- [**bump**][**releng**] Tycho 1.5 and p2 dependencies optimisation [#134](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/134)

#### Release Engineering

- [**releng**] Deploy GEMOC artefacts to maven repository (repo.eclipse.org) [#154](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/154)
- [**releng**] Move some reusable Context and Runconfiguration classes from JavaEngine to execution framework [#144](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/144)
- [**releng**] Improve test video recording [#135](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/135)
- [**releng**] Remove deprecated update site [#130](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/130)
- [**releng**] Improve marker reporting in tests [#120](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/120)

---

## v3.2.0-20191216 (16/12/2019)

#### Enhancements:

- [**enhancement**] Behavioral interface DSL, event occurrence metamodel and value metamodel [#119](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/119)
- [**enhancement**] Add "openFileFromPlugin" method to TestUtil [#109](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/109)
- [**enhancement**] Clarifies Eclipse consoles used by the engines [#116](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/116)

#### Bug Fixes:

- [**bug**] Fix xdsmlFilePath attribute in plugin.xml [#139](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/139)
- [**bug**] Fix double instantiation of language specific addons [#121](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/121)

#### Refactorings

- [**refactoring**] Use general extension point + nature and  builder refactoring [#133](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/133)

#### Version upgrades

- [**bump**] Bump to Sirius 6.1.3 [#138](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/138)
- [**bump**][**releng**] Tycho 1.5 and p2 dependencies optimisation [#134](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/134)

#### Release Engineering

- [**releng**] Improve test video recording [#135](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/135)
- [**releng**] Remove deprecated update site [#130](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/130)
- [**releng**] Improve marker reporting in tests [#120](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/120)

---

## v3.1.0 (29/07/2019)

#### Enhancements:

- [**enhancement**] Add restoreState support for missing eType [#102](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/102)
- [**enhancement**] Display step arguments in the debugger stack view [#59](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/59)
- [**bug**][**enhancement**] Fix sirius wizard call and improve Sirius project creation for GEMOC [#104](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/104)
- [**enhancement**][**releng**] Add concurrent engine to the studio [#98](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/98)
- [**enhancement**] Improve Variable and Debug views [#97](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/97)
- [**enhancement**][**releng**] Add command line Eclipse application to run GEMOC languages and headless product [#90](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/90)
- [**enhancement**] Integrate ALE interpreted engine in GEMOC Studio build [#77](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/77)
- [**enhancement**][**refactoring**] Move dsl default location to project root and minor Melange related refactoring [#76](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/76)

#### Bug Fixes:

- [**bug**] Update launchconf icon according to official colors [#101](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/101)
- [**bug**] Reorder Sirius 4 GEMOC wizard pages [#107](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/107)
- [**bug**] [tracing] Remove exceptions when no traced objects [#100](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/100)
- [**bug**] [test] Adapt test helper code to latest api changes [#99](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/99)
- [**bug**] Fix updating of Sirius views during debugging [#105](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/105)
- [**bug**]  Implement some missing eType in generic trace  and improve user feedback on RDT definition problems [#89](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/89)
- [**bug**] Add restoreState support for missing eType [#94](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/94)
- [**bug**] Fixes GemocSequentialLanguageBuilder problem on windows [#68](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/68)

#### Refactorings

- [**refactoring**] Execution framework refactoring [#53](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/53)

#### Version upgrades

- [**bump**] Bump to eclipse photon [#72](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/72)
- [**bump**] Bump to latest k3/melange version [#64](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/64)
- [**bump**] Bump components and studio versions [#62](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/62)

#### Release Engineering

- [**releng**] Add gexpressions to studio/updatesite and restructure update site [#114](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/114)
- [**releng**] Update icons to official branding colors [#110](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/110)
- [**releng**] Increase swtbot test timeout [#106](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/106)
- [**releng**] Fix xtend compilation issue 1373 [#79](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/79)

---

## v3.1.0-20190627 (27/06/2019)

#### Enhancements:

- [**enhancement**] Add restoreState support for missing eType [#102](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/102)
- [**enhancement**] Display step arguments in the debugger stack view [#59](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/59)
- [**bug**][**enhancement**] Fix sirius wizard call and improve Sirius project creation for GEMOC [#104](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/104)
- [**enhancement**][**releng**] Add concurrent engine to the studio [#98](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/98)
- [**enhancement**] Improve Variable and Debug views [#97](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/97)
- [**enhancement**][**releng**] Add command line Eclipse application to run GEMOC languages and headless product [#90](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/90)
- [**enhancement**] Integrate ALE interpreted engine in GEMOC Studio build [#77](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/77)
- [**enhancement**][**refactoring**] Move dsl default location to project root and minor Melange related refactoring [#76](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/76)

#### Bug Fixes:

- [**bug**] Update launchconf icon according to official colors [#101](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/101)
- [**bug**] Reorder Sirius 4 GEMOC wizard pages [#107](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/107)
- [**bug**] [tracing] Remove exceptions when no traced objects [#100](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/100)
- [**bug**] [test] Adapt test helper code to latest api changes [#99](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/99)
- [**bug**] Fix updating of Sirius views during debugging [#105](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/105)
- [**bug**]  Implement some missing eType in generic trace  and improve user feedback on RDT definition problems [#89](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/89)
- [**bug**] Add restoreState support for missing eType [#94](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/94)
- [**bug**] Fixes GemocSequentialLanguageBuilder problem on windows [#68](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/68)

#### Refactorings

- [**refactoring**] Execution framework refactoring [#53](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/53)

#### Version upgrades

- [**bump**] Bump to eclipse photon [#72](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/72)
- [**bump**] Bump to latest k3/melange version [#64](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/64)
- [**bump**] Bump components and studio versions [#62](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/62)

#### Release Engineering

- [**releng**] Add gexpressions to studio/updatesite and restructure update site [#114](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/114)
- [**releng**] Update icons to official branding colors [#110](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/110)
- [**releng**] Increase swtbot test timeout [#106](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/106)
- [**releng**] Fix xtend compilation issue 1373 [#79](https://github.com/eclipse/gemoc-studio-modeldebugging/pull/79)

---

## v3.0.0 (17/07/2018)
*Includes all changes from 3.0.0 release candidates.*

---

## v3.0.0-rc4 (22/06/2018)
*No changelog for this release.*

---

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
