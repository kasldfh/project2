import sys
fillerLines = "CATEGORY American_mathematician\nIMAGE\nMAIN Boston_University MIT Harvard_University Cornell_University\nTALK\nUSER\nUSER_TALK\nOTHER De:Steven_Strogatz Es:Steven_Strogatz\nEXTERNAL http://www.edge.org/3rd_culture/bios/strogatz.html\nTEMPLATE Cite_book Cite_book Cite_journal\nCOMMENT ISBN formatting &/or general fixes using [[WP:AWB|AWB]]\nMINOR 1\nTEXTDATA 229\n"
inputFile = open("testData.txt", "r")
titleFile = open("titles.txt", "r")
content = [line.strip() for line in inputFile]
titles = [title.strip() for title in titleFile]
for i in range(len(content)):
    chars = list(content[i])
    for n in range(len(chars)):
       print "REVISION", i, "33", titles[i], "Timestamp", chars[n],chars[n], "\n", fillerLines 
