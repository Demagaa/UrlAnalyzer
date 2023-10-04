package cz.seznam.fulltext.robot.processor.api;

import cz.seznam.fulltext.robot.exception.InvalidFileFormatException;

public interface Processor {
    public void process(String regex)  throws InvalidFileFormatException;
}
