package edu.uoc.pac4;

import edu.uoc.pac4.exception.EntityException;

public interface Movable {
    public boolean move(Position position) throws EntityException;
}
