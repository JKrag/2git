package toGit.utils

import org.apache.commons.io.FileUtils
import org.slf4j.LoggerFactory

class FileHelper {

    final static log = LoggerFactory.getLogger(this.class)

    /**
     * Empties the given directory's subdirectories into the given directory, then deletes them.
     * @param directory The directory whose subdirectories to empty/delete
     */
    static void singleFlattenDirectory(File directory) {
        log.debug("Flattening directory $directory")
        def subDirectories = directory.listFiles().findAll { it.isDirectory() }
        subDirectories.each { subDirectory ->
            subDirectory.listFiles().each { source ->
                def target = new File(subDirectory.parentFile, source.name)
                if (target.exists()) {
                    log.debug("Deleting $target")
                    if (target.isDirectory()) target.deleteDir()
                    else target.delete()
                }
                log.debug("Moving $source to $target")
                if (source.directory) FileUtils.moveDirectory(source, target)
                else FileUtils.moveFile(source, target)
            }
            subDirectory.deleteDir()
        }
    }

    /**
     * Deletes the contents of the given directory
     * @param directory the directory to empty
     */
    static void emptyDirectory(File directory) {
        log.debug("Emptying directory $directory")
        directory.listFiles().each {
            if (it.isDirectory()) it.deleteDir()
            else it.delete()
        }
    }
}
