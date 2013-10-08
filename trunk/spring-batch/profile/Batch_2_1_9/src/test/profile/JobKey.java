/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	test.profile.JobKey
 * Date:	Oct 8, 2013  5:42:01 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package test.profile;


/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public final class JobKey extends Key<JobKey> {

    private static final long serialVersionUID = -6073883950062574010L;
    
    public JobKey(String name) {
        super(name, null);
    }

    public JobKey(String name, String group) {
        super(name, group);
    }

    public static JobKey jobKey(String name) {
        return new JobKey(name, null);
    }
    
    public static JobKey jobKey(String name, String group) {
        return new JobKey(name, group);
    }

}
