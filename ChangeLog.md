# ChangeLog for CIlib.

Please note that only noteworthy changes are mentioned for the 0.7.5 release.
Future releases will have more detail.

0.7.5:
 - New clustering implementation.
 - Rework of Niching (NichePSO) to be more sane.
 - Removed the "games" package due to its unmaintained state. It will be
   revised in a future release.
 - Removed several methods that are largely not required (in Meansurements
   and other related classes)
 - FunctionMinimizationProblem and FunctionMAximizationProblem have been
   removed. Instead, use FunctionOptimizationProblem with a given
   Objective. The only valid Objectives are Minimize and Maximize, with
   Minimize being assumed to be the default, if none is specified.
 - Added Lambda-Gamma neural networks
 - Removed a number of interal classes in favor of those already provided
   by dependencies
 - A number of crossover strategies have had fixes applied / newly added
 - Cascading neural networks
 - Migrated from maven to SBT as build system
 - Several deprecations have been removed
 - Additional benchmark functions have been included
 - Updates to XML specifications
 - Added Multiple Velocity Binary PSO
 - More fixes (too many to mention! Please refer to the git history!)
