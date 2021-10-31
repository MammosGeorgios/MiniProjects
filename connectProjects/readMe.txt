Let's say we have a structure like the following

- parentProject
    - src
    - pom.xml
    - child
        - src
        - pom.xml

- parent.pom.xml:
<project>
  <groupId>my.group</groupId>
  <artifactId>parent</artifactId>
  <version>0.0.1</version>
  <packaging>pom</packaging>

  ...

  <modules>
    <module>child</module>
  </modules>
</project>

- child.pom.xml:
<project>
  <artifactId>child</artifactId>
  <packaging>war</packaging>
  <parent>
    <artifactId>parent</artifactId>
    <groupId>my.group</groupId>
    <version>0.0.1</version>
  </parent>

  ...

</project>

-----------------------------------------------------------------------------------------------------------------------
How do we configure the pom files in order to access classes from different subprojects and parent?

A proper solution is to take all the classes from the parent project and create a core project. Then we create a jar from
the core project and use that as a dependency on parent and child projects.

So the updated pom files would look like this:

- parent.pom.xml:
<project>
  <groupId>my.group</groupId>
  <artifactId>parent</artifactId>
  <version>0.0.1</version>
  <packaging>pom</packaging>

  <modules>
    <module>core</module>
    <module>child</module>
    <!-- possibly more modules... -->
  </modules>
</project>

- core.pom.xml:
<project>
  <parent>
    <groupId>my.group</groupId>
    <artifactId>parent</artifactId>
    <version>0.0.1</version>
  </parent>
  <artifactId>core</artifactId>
  <packaging>jar</packaging>
</project>

- child.pom.xml:
<project>
  <parent>
    <groupId>my.group</groupId>
    <artifactId>parent</artifactId>
    <version>0.0.1</version>
  </parent>
  <artifactId>module1</artifactId>
  <packaging>war</packaging>

  <dependencies>
    <dependency>
      <groupId>my.group</groupId>
      <artifactId>core</artifactId>
      <version>${project.version}</version>
    </dependency>
  </dependencies>
</project>

-----------------------------------------------------------------------------------------------------------------------
Extra notes:
- Might have to click the maven refresh button before writing code that used core classes. IDEA will not update the
dependency in code checks and will suggest to add maven dependency which is unnecessary if the above has been done properly.


-----------------------------------------------------------------------------------------------------------------------
Source:
https://stackoverflow.com/questions/10024320/maven-include-parent-classes