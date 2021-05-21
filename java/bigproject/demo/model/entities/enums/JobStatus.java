package bigproject.demo.model.entities.enums;

public enum JobStatus {
    STARTED(1),
    ZAKUPEN(2),
    IZRABOTVAN(3),
    PROVEREN(4),
    IZPRATEN(5),
    PLATEN(6),
    ZAVARSHEN(7);

    public final Integer id;

    private JobStatus(Integer id){
        this.id = id;
    }

    public Integer getId() {
        return id;
    }



    public static JobStatus getStatusById(Integer id){
        for (JobStatus value : JobStatus.values()) {
            if(value.id.equals(id)){
                return value;
            }
        }
        return null;
    }

}
