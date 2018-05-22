package science.mengxin.spring5app.domain;

/**
 * <p>Date:    26/04/18
 *
 * @author mengxin
 * @version 1.0
 */
public enum Difficulty {
    EASY, MODERATE, KIND_OF_HARD, HARD;


    // this method can help template to recognise the values
    public Difficulty[] valuesTemplate(){
        return Difficulty.values();
    }
}
