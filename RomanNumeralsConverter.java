public class RomanNumeralsConverter 
{  
    public int romanToInt(String s) 
    {
        int sum = 0;
        Letter previousLetter = null;
        Letter letter;
        
        for (int i = 0; i < s.length(); i++)
        {
            letter = getLetter(s.charAt(i));
            sum += letter.value;
            
            if (previousLetter != null && previousLetter.ordinal() < letter.ordinal() && previousLetter.ordinal() >= letter.ordinal() - 2)
            {
                sum -= previousLetter.value * 2;
                    previousLetter = null;
            }
            else 
            {
                previousLetter = letter;
            }
        }
        return sum;
    }
    
    Letter getLetter(char c)
    {
        for (int i = 0; i < Letter.values().length; i++)
        {
            if (Letter.values()[i].character == c)
                return Letter.values()[i];
        }
        return null;
    }
}

enum Letter
{
    I('I', 1), V('V', 5), X('X', 10), L('L', 50), C('C', 100), D('D', 500), M('M', 1000);
    
    char character;
    int value;
    
    Letter(char character, int value)
    {
        this.character = character;
        this.value = value;
    }
}