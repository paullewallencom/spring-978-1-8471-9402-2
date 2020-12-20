package org.springaop.chapter.four.introduction;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
public class ParallelepipedIntroduction {

    @DeclareParents(value = "org.springaop.chapter.four.introduction.Box", defaultImpl = Titanium.class)
    public Matter matter;

    @DeclareParents(value = "org.springaop.chapter.four.introduction.Box", defaultImpl = Cube.class)
    public GeometricForm geometricForm;

}
