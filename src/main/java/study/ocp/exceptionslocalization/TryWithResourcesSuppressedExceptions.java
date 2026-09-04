package study.ocp.exceptionslocalization; /*
                             1) autocloseable/closeable
                             2) try with resources

                             */

class TryWithResourcesSuppressedExceptions {
    public static void main(String... args) {
        try (CarDoor door = new CarDoor();
                RoomDoor rd = new RoomDoor()) {
            System.out.println("Try");
            throw new RuntimeException();
        }
        // catch(Exception e){
        // 	// e.printStackTrace();
        // 	System.out.println("Caught: "+e);
        // 	for(Throwable t:e.getSuppressed())
        // 		System.out.println("Suppressed: "+t);
        // }finally{
        // 	System.out.println("Finally!");
        // }
    }
}

class CarDoor implements AutoCloseable {
    public void close() {
        System.out.println("Closing car door");
        throw new RuntimeException("CAnt close car door");
    }
}

class RoomDoor implements AutoCloseable {
    public void close() {
        System.out.println("Closing room door");
        throw new RuntimeException("CAnt close room door");
    }
}
