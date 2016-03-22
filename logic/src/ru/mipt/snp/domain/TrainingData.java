package ru.mipt.snp.domain;

/**
 * <p></p>
 *
 * @author Maxim Galushka
 * @since 05/06/2009  19:48
 */
public class TrainingData {

    private Training trainig;

    // binary file with data 
    private byte[] data;

    public Training getTrainig() {
        return trainig;
    }

    public void setTrainig(Training trainig) {
        this.trainig = trainig;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
