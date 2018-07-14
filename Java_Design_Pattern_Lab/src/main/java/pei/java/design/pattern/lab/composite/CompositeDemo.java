package pei.java.design.pattern.lab.composite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        assertEquals(100, f1.getSize());
        assertEquals(200, f2.getSize());
        assertEquals(300, f3.getSize());
        assertEquals(600, root.getSize());
        assertEquals(500, d1.getSize());
        assertEquals(0, d2.getSize());
        
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
        subFsNodes.addAll(Arrays.asList(nodes));
    }

    public void add(FsNode node) {
        subFsNodes.add(node);
    }

    public void remove(FsNode node) {
        subFsNodes.remove(node);
    }

    public long getSize() {
        size = 0;
        subFsNodes.forEach(node-> size = size + node.getSize()); // the calculation is recursive
        return size;
    }
}

