library(ggplot2)
library(plyr)
library(scales)

ufo <- read.delim(file.path("../data", "ufo", "ufo_awesome.tsv"),
				sep = "\t",
				stringsAsFactors=FALSE,
				header=FALSE,
				na.strings="")

summary(ufo)
head(ufo)
