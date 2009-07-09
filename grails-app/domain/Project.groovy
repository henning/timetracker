/** 
 * Represents a project on which time can be spent
 * 
 * TODO: check how we can get this to implement Taggable!
 * like "class Project implements Taggable{"
 */
class Project {
    static constraints = {
        description(nullable:true)
    }
    
    /** Tags for this Project. */
    static hasMany = [ tags : Tag, timeslices:TimeSlice]

    /** name of the project. */
    String name

    /** a short description of the project */
    String description
    
    String toString(){
        return name 
    }
}
