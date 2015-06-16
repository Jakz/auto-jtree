# Auto-jtree
This small library is meant to generate a working TreeModel from an existing structure through reflection. Future planned features are the ability to edit the nodes (at least the leaf values) and provide custom generators in a manner similar to Google Gson library.

# Usage

Usage is quite straightforward, let have some user defined types like

    public static class Inner2
    {
      public Boolean x[] = new Boolean[]{true, false, false};
      public int t = 20;
    }
    
    public static class Inner
    {
      public List<Double> values;
      public Inner2 inner[] = new Inner2[] { new Inner2(), new Inner2() };
      
      Inner()
      {
        values = new ArrayList<Double>();
        values.add(10.0);
        values.add(20.0);
      }
    }
    
    public static class Test
    {
      
      public float fx = 20.0f;
      public Float fy = 30.0f;
    
      public boolean bx = false;
      public Boolean by = Boolean.TRUE;
      
      public int ix = 50;
      public Integer iy = 70;
      
      public Inner inner = new Inner();
    }

Then to generate a working tree the only code needed is the following

    AutoTree tree = new AutoTree();
	  tree.generate(test);
	  // now AutoTree is a JTree so you are free to use it
