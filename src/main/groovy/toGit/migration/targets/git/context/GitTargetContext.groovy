package toGit.migration.targets.git.context

import org.slf4j.LoggerFactory
import toGit.context.base.Context
import toGit.context.traits.TargetContext
import toGit.migration.targets.git.GitOptions
import toGit.migration.targets.git.GitTarget

class GitTargetContext implements Context, TargetContext {

    final static log = LoggerFactory.getLogger(this.class)

    /**
     * GitOptionsContext constructor
     */
    public GitTargetContext() {
        target = new GitTarget(options: new GitOptions())
    }

    /**
     * Adds given String arguments to the Git ignore file
     * @param args the String arguments to add
     */
    void ignore(String... args) {
        target.options.ignore.addAll(args)
        log.debug("Added $args to git ignore.")
    }

    /**
     * Tracks given String arguments under Git LFS
     * @param args the String arguments to add
     */
    void lfs(String... args) {
        target.options.lfs.addAll(args)
        log.debug("Added $args to git lfs.")
    }

    /**
     * Sets the Git user
     * @param user the user name
     */
    void user(String user) {
        target.options.user = user
        log.debug("Set user to $user.")
    }

    /**
     * Sets the Git user email
     * @param email the user email
     */
    void email(String email) {
        target.options.email = email
        log.debug("Set email to $email.")
    }

    /**
     * Sets the Git path
     */
    void workspace(String path) {
        target.workspace = path
        log.debug("Set workspace to $path.")
    }
}
