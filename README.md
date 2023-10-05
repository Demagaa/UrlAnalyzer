# UrlAnalyzer

The application reads lines from standard input, processes them using one of the supported processors, and writes the results to standard output.

### Processors
Structure of the application's output depends on the processor <code>className</code> used:
####
 ```Top``` - outputs top 10 URLs with highest click count, in the form ```click-count``` (where click-count is a positive integer), sorted from highest click count to lowest click count 
 ####
 ```ContentType``` - for each content type mentioned in the input, outputs a number of URLs having that content type, using the form: ```content-type  url-count``` (where url-count is a positive integer), sorted alphabetically by content type
 ####
 ```Grep``` - outputs all lines in the input that match given regular expression (this processor requires one parameter on the command line - a regular expression)
 
### Input line preferences

Lines read from standard input must have the following format to be considered valid:
 
```url  content-type  click count&gt```
 
 That is, a valid input line contains three values separated by a TAB character, where:
 ####
 ```url``` is a string representing a URL (no need to validate whether the string is actually a valid URL for the sake of this exercise),
 ####
 ```content-type``` is a string representing a HTTP content type (again, no need to validate whether the string represents a valid content type),
 ####
 ```click-count``` is a positive integer representing number of times a particular URL was clicked on (e.g. in search engine results).
 

## Launch:
The application should be invoked as follows (this example assumes unix-like shell is used):
###
```cat input.txt | java -classpath . cz.seznam.fulltext.robot.Runner &lt;className&gt; [&lt;processor parameters&gt;] ```

## Project status

Completed
