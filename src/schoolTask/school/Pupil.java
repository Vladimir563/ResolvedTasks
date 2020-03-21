package schoolTask.school;

public class Pupil extends Human implements IUpKnowledgeLevelable
{
    private String stydingObject;
    private int knowledgeLevel;

    public int getKnowledgeLevel()
    {
        return knowledgeLevel;
    }

    public void setKnowledgeLevel(int knowledgeLevel)
    {
        this.knowledgeLevel = knowledgeLevel;
    }

    public String getStydingObject()
    {
        return stydingObject;
    }

    public void setStydingObject(String stydingObject)
    {
        this.stydingObject = stydingObject;
    }

    public Pupil(String name, int age, String stydOb)
    {
        super(name, age);
        stydingObject = stydOb;
    }

    @Override
    public void upKnowledgeLevel(IUpKnowledgeLevelable pupil)
    {
        pupil = (Pupil) pupil;
        ((Pupil) pupil).knowledgeLevel++;
    }

    @Override
    public String toString() {
        return "Pupil:" + this.getName() + " (stydingObject='" + stydingObject + '\'' + ", knowledgeLevel=" + knowledgeLevel + ") ";
    }
}
