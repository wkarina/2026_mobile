package br.senac.sp.pi.taskManager.domain.entities;

public enum TaskPriority {
    LOW(1), MEDIUM(3), HIGH(5);

    private final int value;


    TaskPriority(int value) {
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public static TaskPriority fromValue( int value ){
        for( TaskPriority priority : TaskPriority.values()){
            if( priority.value == value )
                return priority;
        }
        throw new IllegalArgumentException("Invalid property value: " + value);
    }

}