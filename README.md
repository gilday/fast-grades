# Auto Grader

![TravisCI status](https://travis-ci.org/gilday/fast-grades.png?branch=master)

Automated multiple choice grading application based on BoofCV (computer vision).

Developed with agility for JHU 605.407 - Agile Software Development Methods. See [project page](http://gilday.github.io/fast-grades)

Project uses `sbt` (Scala build tool) for building


## Developed with Agile for JHU 605.407

This is my class project for JHU Agile Software Development Methods (Professor Will Menner)

### Project Proposal

Will use agile methods and practices to develop a multiple choice grading system that uses computer vision to grade scanned multiple choice exams.

My friends asked me if I could make such a tool. They are public school teachers and they dislike their current grading system.

Their current system is specialized hardware that scans proprietary exam sheets. The biggest problem with this system is the manual scanning process: the exams are fed through the scanning hardware sequentially. The second problem with this system is its cost. Newer hardware models can scan batches of exams, but the are very expensive. Furthermore, the exam sheets that students mark their answers on is a proprietary format and it costs significantly more than the paper it's printed on.

My friends want to use a combination of software and the school's existing Xerox machine to implement an affordable, automated grading system. Their Xerox machine is capable of scanning stacks of papers and it emails the digitized pages to teachers' inboxes. Once the exams have been digitized, teachers will use the proposed computer vision software to process the exams. Furthermore, the Xerox machine is also a printer so teachers will use it to print exam sheets instead of buying the costly, proprietary sheets used in the current system.

I am proposing to build the exam processing software and the exam sheet format for this system. My friends have agreed to play the role of the Product Owner. I will meet with the Product Owner to create a prioritized backlog for a minimally operational system. I intend to build on FOSS computer vision software to deliver the minimally functional first release. After the first release of this system, we can start to incorporate teacher feedback to grow the backlog and determine the features and enhancements we would like to have in subsequent releases.


### Simple Model

I created this simple model prior to the story writing workshop to drive discussion

![simple model](https://raw.github.com/gilday/fast-grades/master/docs/simple-model-small.jpeg)

### Agile Practices

**Unit Tests** Using [ScalaTest](fhttp://www.scalatest.org/) for unit testing

**TDD** I'm a big fan of test driven development so I will write my tests at the same time (or before) I write new code. To change existing functionality, I will first change the test to verify the new functionality.

**Automated Build** Using Scala Build Tool ([sbt](http://www.scala-sbt.org/)) to automatically build project artifacts from source

**Continuous Integration** See [Fast Grades on Travis CI](https://travis-ci.org/gilday/fast-grades). With every commit to the `master` branch, Travis CI builds the project and runs the automated tests against JDK 6 and JDK 7 to ensure that the system is compatible with these two platforms

## References

Bookmarks and references

* [Yo + Travis + Github Pages](https://coderwall.com/p/ndaemg) auto deploy a built yeoman project to gh-pages
* [sbt + Grunt](https://github.com/parkotron/sbt-grunt-task) to call Grunt build from sbt
* [Convert PDF](http://www.icesoft.org/wiki/display/PDF/Converting+PDF+Page+Renderings) apache licensed solution for PDF -> BufferedImage
* [JHU VMs](https://support.cs.jhu.edu/wiki/Requesting_A_Virtual_Machine) potential deployment platform
