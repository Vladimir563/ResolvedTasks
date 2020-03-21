package schoolTask.school;

public class Teacher extends Human implements ITeachable
{
    private String teachingObject;

    public String getTeachingObject()
    {
        return teachingObject;
    }

    public Teacher(String name, int age, String teachOb)
    {
        super(name, age);
        teachingObject = teachOb;
    }

    @Override
    public void teach(IUpKnowledgeLevelable pupil)
    {
        pupil.upKnowledgeLevel(pupil);
    }

    @Override
    public String toString()
    {
        return "Teacher: " + this.getName() + " (teachingObject='" + teachingObject + '\'' + ')';
    }
}
