package pei.java.design.pattern.lab.composite;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * the example is a file system tree 
 * 
 * @author pei
 *
 */
public class CompositeDemo {

    @Test
    public void testFileSystemNodes() throws Exception {

        File f1 = new File(100);
        File f2 = new File(200);
        File f3 = new File(300);

        Directory d1 = new Directory(f2, f3);
        Directory d2 = new Directory();
        Directory root = new Directory(d1, d2, f1);

        assertThat(f1.getSize(), is(100L));
        assertThat(f2.getSize(), is(200L));
        assertThat(f3.getSize(), is(300L));
        assertThat(root.getSize(), is(600L));
        assertThat(d1.getSize(), is(500L));
        assertThat(d2.getSize(), is(0L));
        
    }
}


/*
 * File system node, could be file or directory
 */
interface FsNode {
    public long getSize();
}

@AllArgsConstructor @Getter
class File implements FsNode {
    long size;
}

class Directory implements FsNode {
    
    long size = 0;
    private List<FsNode> subFsNodes = new ArrayList<FsNode>();
    
    public Directory(FsNode... nodes) {
        subFsNodes.clear();
        for(FsNode node : nodes) {
            subFsNodes.add(node);
        }
    }

    public void add(FsNode node) {
        subFsNodes.add(node);
    }

    public void remove(FsNode node) {
        subFsNodes.remove(node);
    }

    public long getSize() {
        size = 0;
        for (FsNode node : subFsNodes) {// the calculation is recursive
            size = size + node.getSize();
        }
        return size;
    }
}

