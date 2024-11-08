
<a name="_x28t51ly8gp"></a>**Individual Project:adjusttxt**
## <a name="_1fob9te"></a><a name="_vfatbfivrznf"></a>**Project Goals**
In this project, you will be developing a Java application,adjusttxt, using an agile, test-driven development process across multiple deliverables. For this assignment you will use version 21 of the Java Development Kit. 
## <a name="_3znysh7"></a>**Specification**
adjusttxt is a command-line utility written in Java with the following specification:
### <a name="_2et92p0"></a>**Summary**
adjusttxt allows for simple text manipulation of the contents of a file.
### <a name="_tyjcwt"></a>**Syntax**
adjusttxt [OPTIONS] FILE
### <a name="_3dy6vkm"></a>**Description**
The program adjusttxt performs basic text transformation on the lines of text from an input file. It is invoked as a command-line tool using the [syntax described above](#_tyjcwt), after compilation. The program writes the transformed text to the standard output and errors or usage messages to the standard error without modifying the input file. The FILE parameter is required and must be the last parameter as shown above. The **only options allowed in the program**, which are optional, delimited by the left [ and right ] brackets, may be provided in any order and are described as follows:

|**Option**|**Description**|
| :- | :- |
|-s <number>|**Skip Lines**: Skip the lines based upon the supplied parameter, number. The parameter number can only be 0 or 1. 0 is considered to skip every even line, and 1 is to skip every odd line. The first line in the input file is considered as line 1.|
|-w <spacing>|**Remove Whitespace:** Removes whitespace from lines of the input file based on the specified spacing. The spacing parameter can be one of three case-sensitive string values: "leading", "trailing", or "all". The "leading" string parameter removes whitespace at the beginning of each line, "trailing" removes whitespace at the end of each line, "all" removes all whitespace from the line (beginning, middle, and end).|
|-x|**Remove Empty Lines**: Removes all empty lines from the input file. This option takes no parameters, and is mutually exclusive with the -w option.|
|-r <target>|**Reverse Line**: Reverses all of the content in the line. The target parameter can be one of two case-sensitive string values: “words”, or “text”. The “words” string parameter reverses the order of the words within each line, whereas “text” reverses each line as a whole.|
|-p <prefix>|**Add Prefix**: Adds the string parameter prefix at the start of each line.|
#### <a name="_ihemhkexqzm4"></a>**Order of execution**
The last command-line parameter provided is always treated as the filename, as shown in the [syntax section](#_tyjcwt), while OPTIONS flags can appear in any order and parsed as they appear from left to right. This means that the following two commands are equivalent when executed on the command line:

|Example 1|adjusttxt -p prefix -s 1 input.txt|
| :- | :- |
|Example 2|adjusttxt -s 1 -p prefix input.txt|

In the above examples, (Example 1) parses -p first, then -s, and finally input.txt while (Example 2) parses -s first, then -p, and finally input.txt. These two examples will result in the same output (assuming that the same input.txt is used for both) because the **parsing of options is independent of their execution order**. The order of execution for each option is given in the diagram below (note that the colors and border lines are there for ease of viewing):

![](data:image/png;base64,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)

The above diagram of the execution order of options can also be described as follows:

1. -s shall be processed first, skipping lines depending on 0 or 1.
1. If -w or -x is present, then lines from the input file shall be modified according to its parameter
1. If -r is present, then each line is presented in reverse order according to its parameter.
1. If -p is present, then -p shall pad all lines with a given string
#### <a name="_xxpvs83u82ty"></a>**Requirements**
- To keep this application simple, all errors shall result in the display of the standard usage message:

Usage: adjusttxt [ -s number | -w spacing | -x | -r target | -p prefix ] FILE

- An empty input file shall produce an empty output. The [Examples section](#_2s8eyo1) below shows a case of an empty input file.
##### <a name="_os6ct3ovcsw2"></a>***Java***
- You shall assume that the command line parameter strings will not contain newline characters (\r, \n, \r\n) as the behavior of the program would be platform dependent and may result in error during grading. Therefore there should be no test cases using these values as option parameters. Additionally, you may assume that your application will be called with valid String[] args.
- **Make sure not to make calls to System.exit() within your tests, as that creates problems for JUnit**.
##### <a name="_rinan2mp8u25"></a>***Options***
- If options are repeated, only their last occurrence is applied. The [Examples section](#_2s8eyo1) below shows a case of repeated options.
#### <a name="_6kvspkduq7qc"></a>**
#### <a name="_4d34og8"></a>**Program errors**
##### <a name="_nq6386p62bl6"></a>***FILE errors***
- The last line of a **non-empty** input file must be terminated by a newline. If the non-empty input file does not terminate in a newline, the program shall generate an error.
- The last parameter is always assumed to be a path to the input file. Omitting it shall result in an error otherwise.
##### <a name="_ckj93p3jfgil"></a>***Option errors***
- All parameters of program options are required and shall result in an error if omitted. This means that parsing an option that should include parameters but doesn't should result in an error. The [Examples section](#_2s8eyo1) below shows a case of missing option parameters.
- Any unrecognized option shall result in an error.

The cases below show scenarios where adjusttxt shall result in an error according to a specific option.

|**Options**|**Error Cases**|
| :- | :- |
|-s|<p>- Providing an integer outside of the inclusive range 0 to 1 as the skip parameter.</p><p>- Providing a non-integer as the skip parameter.</p>|
|-w|<p>- Providing a string value outside "leading", "trailing", or "all" as the parameter.</p><p>- Providing this option with -x.</p>|
|-x|- Providing this option with -w.|
|-r|- Providing a string value outside “words” or “text” as the parameter.|
|-p|- Providing an empty string for the prefix parameter.|
### <a name="_mzfg29tzz2rt"></a>**
### <a name="_2s8eyo1"></a>**Examples of Usage**
The examples described here can also be seen in JUnit 5 form on the MainTest.java file [provided to you in the below sections](#_3whwml4). In the following, "↵" represents a newline character.

|Example 1||
| :- | :- |
|adjusttxt sample.txt||
|input sample.txt||
|stdout|**nothing sent to stdout**|
|stderr|**nothing sent to stderr**|

|Example 2||
| :- | :- |
|adjusttxt -x -p some\_prefix sample.txt||
|input sample.txt|↵|
|stdout|**nothing sent to stdout**|
|stderr|**nothing sent to stderr**|

|Example 3||
| :- | :- |
|adjusttxt -s 2 sample.txt||
|input sample.txt|<p>Hello, world!↵</p><p>↵</p><p>How are you?↵</p><p></p>|
|stdout|**nothing sent to stdout**|
|stderr|Usage: adjusttxt [ -s number | -w spacing | -x | -r | -p prefix ] FILE↵|



|Example 4||
| :- | :- |
|adjusttxt -s 1 sample.txt||
|input sample.txt|<p>Okay, here is how this is going to work.↵</p><p>No shouting!↵</p><p>Does that make sense?↵</p><p>Alright, good meeting.↵</p><p></p>|
|stdout|<p>No shouting!↵</p><p>Alright, good meeting.↵</p><p></p>|
|stderr|**nothing sent to stderr**|

|Example 5||
| :- | :- |
|adjusttxt -p user@ubuntu:~$ sample.txt||
|input sample.txt|<p>pwd↵</p><p>cd Documents↵</p><p>echo “This is a test” > echoed\_sample\_text.txt↵</p><p>cat echoed\_sample\_text.txt↵</p><p>grep test echoed\_sample\_text.txt↵</p><p>rm echoed\_sample\_text.txt↵</p><p></p>|
|stdout|<p>user@ubuntu:~$pwd↵</p><p>user@ubuntu:~$cd Documents↵</p><p>user@ubuntu:~$echo “This is a test” > echoed\_sample\_text.txt↵</p><p>user@ubuntu:~$cat echoed\_sample\_text.txt↵</p><p>user@ubuntu:~$grep test echoed\_sample\_text.txt↵</p><p>user@ubuntu:~$rm echoed\_sample\_text.txt↵</p><p></p>|
|stderr|**nothing sent to stderr**|



|Example 6||
| :- | :- |
|adjusttxt -w all -w trailing -w leading sample.txt||
|input sample.txt|<p>`    `The vibrant red roses bloomed in the garden↵</p><p>`    `She wore a beautiful blue dress to the party↵</p><p>`    `The sky turned into a brilliant shade of blue↵</p><p>`    `His favorite color is red, her favorite is blue↵</p><p></p>|
|stdout|<p>The vibrant red roses bloomed in the garden↵</p><p>She wore a beautiful blue dress to the party↵</p><p>The sky turned into a brilliant shade of blue↵</p><p>His favorite color is red, her favorite is blue↵</p><p></p>|
|stderr|**nothing sent to stderr**|

|Example 7||
| :- | :- |
|adjusttxt -x sample.txt ||
|input sample.txt|<p>Okay, let’s start counting.↵</p><p>↵</p><p>1\.↵</p><p>↵</p><p>2\.↵</p><p>↵</p><p>3\.↵</p><p></p>|
|stdout|<p>Okay, let’s start counting.↵</p><p>1\.↵</p><p>2\.↵</p><p>3\.↵</p><p></p>|
|stderr|**nothing sent to stderr**|



|Example 8||
| :- | :- |
|adjusttxt -r words sample.txt||
|input sample.txt|<p>Write your name.↵</p><p>Write the date.↵</p><p>Answer questions 1-4.↵</p><p>Ignore all other instructions and turn this in as-is.↵</p><p></p>|
|stdout|<p>name. your Write↵</p><p>date. the Write↵</p><p>1-4. questions Answer↵</p><p>as-is. in this turn and instructions other all Ignore↵</p><p></p>|
|stderr|**nothing sent to stderr**|

|Example 9||
| :- | :- |
|adjusttxt -w all -s 1 -s 0 sample.txt||
|input sample.txt|<p>It’s clobbering time!↵</p><p>\*Punches hole into brick wall\*↵</p><p>Whoops!↵</p><p></p>|
|stdout|<p>It’sclobberingtime!↵</p><p>Whoops!↵</p><p></p>|
|stderr|**nothing sent to stderr**|



|Example 10||
| :- | :- |
|adjusttxt -p -x sample.txt||
|input sample.txt|<p>red paint goes well with purple paint.↵</p><p>↵</p><p>teal is a type of blue and green.↵</p><p>↵</p><p>roses are either red or purple.↵</p><p></p>|
|stdout|<p>-xred paint goes well with purple paint.↵</p><p>-x↵</p><p>-xteal is a type of blue and green.↵</p><p>-x↵</p><p>-xroses are either red or purple.↵</p><p></p>|
|stderr|**nothing sent to stderr**|
##

|<a name="_lk55jogop6wq"></a>Example 11||
| :- | :- |
|adjusttxt -s 0 -w leading -p ## -r text sample.txt||
|input sample.txt|<p>`    `Once upon a time, here was a hen.↵</p><p>`    `When this hen left the den, it roamed all of the land↵</p><p>`      `All of it, until the hen got to the end.↵</p><p></p>|
|stdout|<p>##.neh a saw ereh ,emit a nopu ecnO↵</p><p>##.dne eht ot tog neh eht litnu ,ti fo llA↵</p><p></p>|
|stderr|**nothing sent to stderr**|
## <a name="_phegtt9bfi7"></a>**
### <a name="_26in1rg"></a><a name="_3rdcrjn"></a>**Deliverable 1**
**Provided**

- adjusttxt specification ([linked in this document above](#_3znysh7))
- Skeleton of the main class for adjusttxt ([linked below](#_3whwml4))
- JUnit 5 example tests and skeleton of test class to submit ([linked below](#_3whwml4))
- JUnit 5 library ([linked below](#_3whwml4))

**Expected**

- Part I (Category partition)
  - catpart.txt: TSL file you created
  - catpart.txt.tsl: test specifications generated by the TSLgenerator tool when run on your TSL file
- Part II (JUnit tests)
  - MyMainTest.java: JUnit tests derived from your category partition test frames
###
## <a name="_3v4qkmjtm175"></a><a name="_2xcytpi"></a><a name="_lnxbz9"></a>**Instructions**
Deliverable 1 is split up in two parts: [Part I](#_1ci93xb) and [Part II](#_3whwml4). Follow the instructions for each of the parts as described below.
### <a name="_1ci93xb"></a>**Part I**
Your task for this deliverable is to generate 50 to 90 (inclusive) test frames for the adjusttxt utility using the category-partition method presented in lesson P4L2. 

When defining your test specifications, your goal is to suitably cover the domain of the application under test, including relevant erroneous input and input combinations. For example, if you were testing a calculator, you may want to cover the case of division by zero.

**Do not manually generate combinations of inputs as single choices**. Instead, use multiple categories and choices with necessary constraints for the tool to generate meaningful combinations. Using the calculator example, you should not offer choices "add", "multiple", and also "add and multiply" in a single category - an example of what **not to do** can be found in [calculator-manual-recombination.](.) In particular, make sure to use constraints (error and single), selector expressions (if), and properties appropriately, rather than eliminating choices, to keep the number of test cases within the 50 to 90 inclusive range.

The domain for this assignment is the Java application, so anything that the shell would reject, such as unmatched double quotes, will not reach the application. This means that you must test for invalid input arguments (such as Example 3 above), but you don't need to test for errors involving parsing the command-line arguments before they're sent to the Java application. In addition, you may assume that main will be called with a valid args array, meaning that values like null will not be passed.
#### <a name="_6aksms3x82t6"></a>**Tools and Useful Files**
You will use the TSLgenerator tool to generate test frames starting from a TSL file, just like we did in the demo for lesson P4L2. Versions of the TSLgenerator for Linux, Mac OS, and Windows, together with a user manual, are available at:

- [TSLgenerator-manual.txt](.)
- [TSL generator for Linux](.)
- [TSL generator for Mac OS](.)
- [TSL generator for Windows 8 and newer](.)
- [TSL generator for Windows XP and earlier](.)



We are also providing the TSL file for the example used in the lesson, [cp-example.txt](.), for reference, as well as an example for explaining <n/a> values, [tsl-na-example.md](.).

Since the TSL generator is a command-line tool, it must be run from the command line, as we do in the video demo, rather than by clicking on them. The syntax for running the tool is

<tsl> [--manpage] [-cs] infile [-o outfile]

where <tsl> is the name of the TSLgenerator executable and infile is the input file to the TSL program, i.e., the catpart.txt file. You can find out more information by running the tool with the --manpage command, which prints the manual of the TSL generator<sup>[^1]</sup>.

For reference, Gradescope will execute the tool on a Linux platform.
### <a name="_fi37vwpaxgqx"></a><a name="_akgbpb3n3m9f"></a>**
### <a name="_3whwml4"></a>**Part II**
In this second part of the deliverable, you will create actual test cases implementing the test specifications you created in Part I. As discussed in the lesson on the category-partition method, each test frame is a test specification that can be instantiated as an individual concrete test case. To do so, you should perform the following tests:

1. Download archive [individua.lproject-d1.tar.gz](.)
1. Unpack the archive in the directory "IndividualProject", which you created in Part I of the deliverable. Hereafter, we will refer to this directory as <dir>. After unpacking, you should see the following structure:
   1. <dir>/adjusttxt/src/edu/gatech/seclass/adjusttxt/Main.java

      This is a skeleton of the Main class of the adjusttxt utility, which we provide so that the test cases for adjusttxt can be compiled. It contains an empty main method and a method usage, which prints, on standard error, a usage message that should be called when the program is invoked incorrectly. In case you wonder, this method is provided for consistency in test results.

   1. <dir>/adjusttxt/test/edu/gatech/seclass/adjusttxt/MainTest.java

      This is a test class with a few test cases for the adjusttxt utility that you can use as an example; it corresponds to the examples of usage of adjusttxt that we provided. In addition to providing this initial set of tests, class MainTest also provides some utility extensions and methods that you can leverage/adapt and that may help you implement your own test cases. **We encourage you to use the methods to ease your design process**.

   1. <dir>/adjusttxt/test/edu/gatech/seclass/adjusttxt/MyMainTest.java

      This is an empty test class in which you will add your test cases, provided for your convenience.

   1. <dir>/adjusttxt/test/edu/gatech/seclass/adjusttxt/OutputCapture.java

      This is a JUnit 5 extension class to facilitate capturing the standard output and standard error, which are needed to test the adjusttxt application. It is used on the MainTest.java file for reference and provides two methods to capture output from Main.

   1. <dir>/adjusttxt/lib/junit-platform-console-standalone-1.9.1.jar

      JUnit 5 library to be used for the assignment.

1. Use the test frames from Part I to generate additional JUnit test cases for the adjusttxt utility, one per frame, and put them in the test class MyMainTest. For ease of test design, it's recommended that you name your test cases adjusttxtTest1, adjusttxtTest2, and so on. It's required that each test contains a concise comment that indicates which test frame the test case implements. It should at least have the following comment format:

// Frame #: <test case number in file catpart.txt.tsl>
#### <a name="_77oi44y78hwn"></a>**Designing your tests**
Your test frames should contain enough information to create relevant test cases. **If you cannot implement your test frames as useful JUnit tests** (e.g., because the test frames do not provide enough information)**, you should revisit Part I.** Extending the calculator example, if your test frame specified a numerical input, and you realized that you should use both negative and positive numbers in your JUnit test case, you should revise your categories and choices so that this is reflected in your test frames.

If you are uncertain what the result should be for a test, you may make a reasonable assumption on what to use for your test oracle. While you should include a test oracle, **we will not grade the accuracy of the test oracle itself**. Feel free to reuse and adapt, when creating your test cases, some of the code we provided in the MainTest class. MainTest is provided for your convenience and to help you get started. Whether you leverage the MainTest class or not, your test cases should assume (just like the test cases in MainTest do) that the adjusttxt utility will be executed from the command line, as follows:

java -cp <classpath> edu.gatech.seclass.adjusttxt.Main <arguments>

For this deliverable, **do not implement the adjusttxt utility**, but only the test cases for it. This means that most, if not all of your test cases will fail, which is expected and fine.
#### <a name="_jm1j62lzsfgv"></a><a name="_2korgbvrpwoj"></a>**As soon as you submit, Gradescope will verify your submission by making sure that your files are present and in the correct location, as well as a few additional minor checks.<sup>[^2]</sup> If you pass all of these checks, you will see a placeholder grade of 10 and a positive message from Gradescope. Otherwise, you will see a grade of 0 and an error message with some diagnostic information. Please note that:**
- a positive response from Gradescope only indicates that you passed the initial checks and is meant to prevent a number of trivial errors;
- **if your submission does not pass the Gradescope checks, it will receive a 0**, so please make sure to pay attention to the feedback you receive when you submit and keep in mind that you can resubmit as many times as you want before the deadline. Once the assignment closes, we will not be able to regrade assignments that fail these Gradescope checks.

  <a name="_pxblhj4rpkpc"></a><a name="_gng4dhn6xw0u"></a>ture and test methods provided.
14

[^1]: On Linux and Mac systems, you may need to change the permissions of the files to make them executable using the chmod utility. To run the tool on a Mac for instance, you should do the following, from a terminal:

    chmod +x TSLgenerator-mac
[^2]: 