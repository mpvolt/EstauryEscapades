JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
CLASSES = \
	GameMode.java \
	Direction.java \
	Animal.java \
	Scuba.java \
	Bass.java \
	Octopus.java \
	Anchovy.java \
	Bird.java \
	Clam.java \
	Crab.java \
	HorseShoeCrab.java \
	Question.java \
	TitleView.java \
	ScubaTutView.java \
	AnimalTutView.java \
	GameModel.java \
	GameView.java \
	QuizModel.java \
	QuizView.java \
	FFView.java \
	Controller.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
