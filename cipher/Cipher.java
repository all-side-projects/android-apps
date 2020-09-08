package ca.yorku.eecs.kryptonote;

public class Cipher
{
    public static final String ALPHABET = " ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
    private String key;
    public Cipher(String k)
    {
        this.key = k;
    }

    private String makePad(String note)
    {
        String pad = this.key;
        for (; pad.length() < note.length();)
        {
            pad += this.key;
        }
        return  pad;
    }

    public String encrypt(String note)
    {
        String pad = makePad(note);
        String result = "";
        for (int i = 0; i < note.length(); i++)
        {
            String c = note.substring(i, i + 1);
            int position = ALPHABET.indexOf(c);
            int shift = Integer.parseInt(pad.substring(i, i + 1));
            int newPosition = (position + shift) % ALPHABET.length();
            result = result + ALPHABET.substring(newPosition, newPosition + 1);
        }
        return result;
    }

    public String decrypt(String note)
    {
        String pad = makePad(note);
        String result = "";

        for (int i = 0; i < note.length(); i++)
        {
            String c = note.substring(i, i + 1);
            int position = ALPHABET.indexOf(c);
            int shift = Integer.parseInt(pad.substring(i, i+1));
            int newPosition = (position - shift);

            if (newPosition < 0)
            {
                newPosition += ALPHABET.length();
            }

            result = result + ALPHABET.substring(newPosition, newPosition + 1);
        }
        return result;
    }

    public static void main(String[] args)
    {
        Cipher myCode = new Cipher("1234");
        System.out.println(myCode.decrypt("UJLWAKVDBBWITV"));
    }

}
