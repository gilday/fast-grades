# Fast Grades

[![Build status](https://travis-ci.org/gilday/fast-grades.png?branch=master)](https://travis-ci.org/gilday/fast-grades)

[Production Instance](http://gilday.github.io/fast-grades)

Automated multiple choice grading application based on BoofCV (computer vision).

Developed with agility for JHU 605.407 - Agile Software Development Methods with Professor Will Menner

## Project Proposal

Will use agile methods and practices to develop a multiple choice grading system that uses computer vision to grade scanned multiple choice exams.

My friends asked me if I could make such a tool. They are public school teachers and they dislike their current grading system.

Their current system is specialized hardware that scans proprietary exam sheets. The biggest problem with this system is the manual scanning process: the exams are fed through the scanning hardware sequentially. The second problem with this system is its cost. Newer hardware models can scan batches of exams, but the are very expensive. Furthermore, the exam sheets that students mark their answers on is a proprietary format and it costs significantly more than the paper it's printed on. In fact, sometimes the school runs out of these exam sheets and does not have a budget to buy more.

My friends want to use a combination of software and the school's existing Xerox machine to implement an affordable, automated grading system. Their Xerox machine is capable of scanning stacks of papers and it emails the digitized pages to teachers' inboxes. Once the exams have been digitized, teachers will use the proposed computer vision software to process the exams. Furthermore, the Xerox machine is also a printer so teachers will use it to print exam sheets instead of buying the costly, proprietary sheets used in the current system.

I am proposing to build the exam processing software and the exam sheet format for this system. My friends have agreed to play the role of the Product Owner. I will meet with the Product Owner to create a prioritized backlog for a minimally operational system. I intend to build on FOSS computer vision software to deliver the minimally functional proof of concept. After the first release of this system, we can start to incorporate teacher feedback to grow the backlog and determine the features and enhancements we would like to have in subsequent releases.


## Simple Model

I created this simple model prior to the story writing workshop to drive discussion

![simple model](https://raw.github.com/gilday/fast-grades/master/docs/simple-model-small.jpeg)

## Demonstration

At this stage, the system is a proof of concept which is able to grade an exam. 

First step is to create the exam by providing the name and number of questions

![Step One](https://raw.github.com/gilday/fast-grades/master/docs/sshot-step-one.png)

Next, enter the answer key using the form so the system is aware of the correct answers.

![Step Two](https://raw.github.com/gilday/fast-grades/master/docs/sshot-step-two.png)

Use the links to print the generated exam sheet for the class

![Step Three](https://raw.github.com/gilday/fast-grades/master/docs/sshot-step-three.png)
![Print Exam](https://raw.github.com/gilday/fast-grades/master/docs/sshot-print-exam.png)

After filling out the printed exam, scan the exam as an image and upload it using the form.

![Fill Exam](https://raw.github.com/gilday/fast-grades/master/docs/whole-sheet.jpg)
![Upload Exam](https://raw.github.com/gilday/fast-grades/master/docs/docs/sshot-upload-exam.png)

The system responds to the file upload with a grade report screen.

![Grade Report](https://raw.github.com/gilday/fast-grades/master/docs/sshot-grade-report.png)

## Process

Fast Grades is developed using an agile process based on Scrum and adapted for single developer, part-time development. 

Parts of Scrum I adopted

1. User stories and story writing workshops
2. Story estimation and prioritization in a product backlog
3. Burndown chart to measure velocity
4. Sashimi - address all aspects of the system in order to get it minimally working as soon as possible so the product owners can "touch it"
5. Sprint backlogs with stories broken into tasks for 2 - 3 week sprints. The first sprint was 3 weeks and all later sprints were 2 weeks.
6. Let the number of stories completed in the previous sprint drive the size of the next sprint
7. Retrospectives done at the conclusion of each sprint and documented in the sprint backlog

## Agile Practices

**Automated Build** Using Scala Build Tool ([sbt](http://www.scala-sbt.org/)) to automatically build project artifacts from source

**Unit Tests** Using [ScalaTest](fhttp://www.scalatest.org/) for unit testing

**TDD** I will write my tests at the same time (or before) I write new code. To change existing functionality, I first change the test to verify the new functionality. 
Currently, the automated testing is done in ScalaTest for the backend code. The front-end JavaScript is not unit tested because I lack experience with the tricky world of JavaScript automated testing and the front-end is relatively simple in comparison to the complexity of the backend. 

![Automated Tests](https://raw.github.com/gilday/fast-grades/master/docs/sshot-tests.png)

**Continuous Integration** See [Fast Grades on Travis CI](https://travis-ci.org/gilday/fast-grades). With every commit to the `master` branch, a Github post-commit hook tells Travis CI to build the project and run the automated tests.

**Continuous Deployment** The [Travis CI yaml file](https://github.com/gilday/fast-grades/blob/master/.travis.yml) configures Travis CI to push the both the front-end and back-end code to their respective hosting platforms. The front-end web code is hosted on Github pages at http://gilday.github.io/fast-grades. The backend scala code is hosted on Heroku at http://fast-grades.herokuapp.com/

![Continuous Integration](https://raw.github.com/gilday/fast-grades/master/docs/sshot-travis.png)

## Definition of Done

In general, I will define the definition of done to be the following. 

All commits should be merged into the master branch. Pushing the master branch to GitHub will trigger the continuous integration system to run all the automated tests and deploy the system.
Since the master branch automatically deploys the front and back end, I will quickly verify that the production system behaves as expected in the production environment.

## Product Backlog

[The product backlog is implemented as a Google Spreadsheet](https://docs.google.com/spreadsheet/ccc?key=0AteH1qcLQxi-dGxkRHR2YjdQWEMxLWx2WWVLZ05HZlE&usp=sharing). The product owners have the ability to add comments to the spreadsheet. 
The spread sheet is influenced by the template in the class resources. The sheet the following worksheets

1. Release Plan: lists the planned sprints in chronological order with dates
2. Product Backlog: All user stories prioritized
3. Tech Tasks: Infrastructure and Technical Debt tasks which are not user stories but need to be estimated and planned for
4. Velocity: burndown chart plots the number of story points remaining after each sprint
5. Sprint Backlogs: one worksheet for each Sprint which lists the planned and completed stories and tasks for that sprint.

Before I implemented the product backlog spreadsheet, I tried to implement the backlog just using Github Milestones and Issues. These Github features were nice because they were well integrated with the Github platform but the Issues and Milestones were not easy to organize in a manner that is consistent with class discussions. 

I corrected course and I now keep the product backlog in the aforementioned spreadsheet. In addition to the spreadsheet, I also input the stories for the current sprint into Github Issues. I group the [Issues](https://github.com/gilday/fast-grades/issues?state=open) into [Milestones](https://github.com/gilday/fast-grades/issues/milestones) (which correspond to each sprint). By keeping the current sprint's stories as Issues in Github, I can associate each code commit and branch with a task. These Issues are also more comfortable to work with while coding; however, this duplicates product backlog data so I would not do this again. Instead, I would prefer to use a system like Jira which is capable for associating code commits with tasks but is more full featured than Github Issues and more tailored to this domain than spreadsheets.

In my product backlog, I separated user stories and techincal tasks (infrastructure and tech debt) in order to track their velocity separately. The burndown chart shows the technical task points stacked on top of the user story points. This system allowed the product owners to see progression on technical tasks vs their stories. 

### Story Writing Workshop

![Story Writing Workshop](https://raw.github.com/gilday/fast-grades/master/docs/story-workshop.png)

The first tasks for this project were part of the feasability study. I have no computer vision experience so I wanted to make sure I could do this project in a semester. After concluding that the project is feasible, I conducted a story writing workshop with the product owners. Four themes emerged from the story writing workshop:

1. Optical Grading
2. Organize Classes 
3. Analytics
4. Student Participation

The stories that came out of the workshop were large and ambitious. After the workshop, I had to manage the product owners' expectations and inform them that I will only have enough capacity to focus on the "Optical Grading" theme. This theme contains the core set of essential features which enable the other themes.

The stories in the "Optical Grading" theme are also large and I will focus on this theme first. I will defer estimating stories and epics in low priority themes until the system is further developed. 

In addition to the four themes, two user roles emerged from the workshop:

1. Teachers
2. Students

The vast majority of the user stories were for the teacher role. This makes sense because this is the system's primary user and both the product owners are teachers. The student role was introduced in the "Student Participation" theme. In this theme, the product owners imagined that students could also have accounts with the system such that they could see their grades for all the classes the belong to. 

### Estimation

As discussed in class, I used relative estimation to arrive at the point values for stories and tasks. Therefore, the points themselves do not translate to hours; rather, an 8 point story is 4 times as large as a 2 point story. For user stories and tasks, I used a subset of the Fibonacci series: 1, 2, 3, 5, 8, 13 for point values. I chose from this set of values as if it were my hand in planning poker. In retrospect, planning poker might not have been the best estimation technique since I do not have team members to discuss estimations with. Menner's Method is probably better suited for this project. 

For epics, I chose from the set 20, 30, 40, 50, 60, 70, 80, 90, 100. I only estimated one epic because the other epics were a low priority so it was best to defer estimation. 

### Prioritization

At the project's onset, infrastructure tasks, such as hosting the website, build system, test framework, and continuous integration server, took precedence. After these tasks were complete. The next priority were stories from the "Optical Grading" theme which was the focus of my work this semester. Using Kano analysis, the stories in the Optical Grading theme are mandatory features, stories in the Organize Classes theme are linear, and stories in the Analytics and Student Participation themes are exciters. 

## Project Retrospective

My experience on this project has encouraged me to introduce estimation, product backlog prioritization, and continuous deployment to my professional assignment. 

At work, we use Jira to maintain our backlog; however, we do not prioritize the backlog nor do we estimate tasks. Estimating tasks will help us determine the level of effort for new tasks so we can better communicate the level of effort the product owner. Also, the burndown chart in our Jira system is not accurate because it only counts number of tasks and does not account for the size of the tasks.

At work, my team generally discusses a rough prioritization for the next couple of weeks, but I think it is helpful to keep the whole backlog prioritized. This practice removes any "what should we work on next" guessing. Keeping the backlog prioritized also helps with adding new tasks. It's easy to start working on new tasks right away because they are fresh but being fresh does not always translate to highest value. 

My system at work is arduous to deploy right now so I am especially interested in continuous deployment. I spent a lot of time setting up continuous deployment for Fast Grades so I could experience development with a system like this in place. I was impressed with the tools available for this space and I liked the Github + Travis-CI + Heroku setup. Having this system in place allowed me to keep the system always working in production instead of finding out in the last week that there was a problem with deployment. The continuous delivery system also allowed the product owners to stay up to date with my progress not only at the end of a sprint but every time I finished a task. 


In hindsight, I would do some things differently if I did this project again. 

I would have held a second story writing workshop after I managed the product owners' expectations. I didn't want to manage their expectations during the workshop because I wanted their ideas to flow freely. Afterwards, I should have held another workshop so we could further develop the themes and stories that were in the scope of this semester project. 

I would take less technology risks. I tried to squeeze some new, unfamiliar technologies into this project so I could gain experience with them. In Sprint 4, I replaced these risky technologies with familiar alternatives to increase my velocity; however, I wish I had done this in Sprint 2 or earlier. 

I wouldn't bother with the Github Issues for tracking tasks; rather, I would keep the product backlog exclusively in the spreadsheet or use a different system such as Jira. I find Jira to be a useful tool and it would save me from fighting with the spreadsheet. 

Lastly, I would have started breaking stories into tasks in the Sprint Backlog earlier than Sprint 5. I struggled with large user stories in each of my Sprint Backlogs. Based on feedback from the project checkup, I started breaking the stories into tasks in Sprint 5 but I wish I had done this earlier.

## Bookmarks and References

* [Yo + Travis + Github Pages](https://coderwall.com/p/ndaemg) auto deploy a built yeoman project to gh-pages
* [sbt + Grunt](https://github.com/parkotron/sbt-grunt-task) to call Grunt build from sbt
* [Convert PDF](http://www.icesoft.org/wiki/display/PDF/Converting+PDF+Page+Renderings) apache licensed solution for PDF -> BufferedImage
* [JHU VMs](https://support.cs.jhu.edu/wiki/Requesting_A_Virtual_Machine) potential deployment platform
