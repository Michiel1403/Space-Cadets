#Space Cadets Challenge 1
Challenge Description from HTML file in repository:

The colonies have been attacked by an alien race and human civilisation has been decimated. It is known how many survivors are left and much of the infrastructure is still intact. You are onboard one of the few remaining vessels, on a team tasked with making contact with the surviving science team on earth to rebuild our research and development, and fight back.

Reading and Writing Strings from Files and URLs
Have a look at the Web page at https://www.ecs.soton.ac.uk/people/dem. This is a departmental information page which gives all sorts of information about a member of staff. The Web address is constructed from a departmental email id (in this case dem). If I have someone else's email id, I can look up their name from one of these Web pages. Try it with your own email id. In fact, in the past the name started at the 12th character of the 6th line of the HTML data returned by the Web server. It finishes when a '<' character appears. (Choose 'View Source' from your Web browser to check where it is now.)

Write a program which converts an email id into a name by

Constructing a BufferedReader object so that can read an email id from System.in (you will need some intermediate objects to help you here. Look it up!)
Constructing the full Web page address by string concatenation
Constructing a URL object from the Web address
Constructing a BufferedReader object that can read from the URL (you will need some intermediate objects to help you here. Look in the book!)
Ignoring the first lines of input from the Web page and saving the one which contains the name (Hint: <... property="name">)
Use the indexOf() and substring() methods to find and extract the name from the line
Print out the result
This sounds quite complex, but it is just a longer variation on the things you will already have seen in the book and in your labs. It's actually quite simple, but as in all programming, the devil is in the details!

Advance Variations
This kind of data extraction is actually quite simple because we know exactly where the data will appear in the file - it gets much more difficult when we don't know the exact format of the page returned to us.

Space Cadets is based on the idea of a central challenge that people are encouraged to extend - or even bypass altogether! Why not have a go at some variations of this theme (or add your own suggestions to the list below):

write a program to get related people for this page for example https://secure.ecs.soton.ac.uk/people/dem/related_people [Please note you have to authenticate to enter this page]
Write a program to find the URL of anyone's home page by using the first page that Google returns (try Joyce Lewis, Queen Elizabeth, Paul Whitehouse, Tinky Winky, Kylie Minogue...).
Write a program that prints anagrams of your name by invoking the Internet Anagram Server at http://wordsmith.org/anagram/
Write a program that converts from English to French by using the Google Translate web page at http://translate.google.com/. This is a little more complex because it requires an HTTP POST rather than a GET and the page has been 'minified' which makes it difficult to read. (We used to suggest you try http://babelfish.yahoo.com/, but that's been Bing'd and now requires Javascript to work - Toby Hunt (Systems staff))
Write a program that creates a .txt file with information (how much is at your discretion) about the person (If you REALLY want to expand on that, automate it to go through EVERY page)
Extra Extensions
If you are feeling really ambitious why not have a go at writing a Java program that talks to Twitter. Twitter has a public HTTP-based API that you can look at. One interesting challenge would be to take a user id as input and then look up followers to find out what hashtags that a group of users has been using.

Remember that Twitter limits the number of requests per hour, and has been known to ban users or IPs if they overuse the Twitter servers, so be careful not the send too many requests. Especially when you are developing and testing your code.

Know How
Using Regular expression will make your life easier when you are searching or matching patterns

Learn about it -- https://www.regular-expressions.info
Play with it -- https://regexr.com
Quick and dirty -- https://docs.microsoft.com/en-us/dotnet/standard/base-types/regular-expression-language-quick-reference
