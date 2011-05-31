int i = 5
System.out.println(i.class)

"""
Remember, this is a 'heredoc' string, 
which can take lots and
lots of lines and do stuff
""".eachLine({ line -> println("==> $line")})

def text = """
<characters>
<props>
<prop>dd</prop>
</props>
<character id="1" name="Wallace">
<likes>cheese</likes>
</character>
<character id="2" name="Gromit">
<likes>sleep</likes>
</character>
</characters>
"""

def node = new XmlSlurper().parseText(text);
def characters = node.character
for (c in characters) { println c['@name'] }

def gromit = node.character.find { it['@name'] == 'Gromit' }
println "gromit likes " + gromit.likes.text()
def whatDoesWallaceLike = node.character.find { it['@name'] == 'Wallace' }.likes.text()
println "What does Wallace like? $whatDoesWallaceLike"
