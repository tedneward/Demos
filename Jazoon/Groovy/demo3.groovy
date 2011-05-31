import java.math.BigDecimal as Decimal

public class Complex {
    Decimal real
    Decimal imaginary
    
    def Complex plus(Complex rhs)
    {
        if (rhs)
            return new Complex(real: this.real + rhs?.real,
                    imaginary: this.imaginary + rhs?.imaginary)
        else
            return this
    }
    
    def String toString()
    {
        "[$real, ${imaginary}i]"
    }
}

Complex c1 = new Complex(real: new Decimal("40183740"),
                imaginary: new Decimal("84736493"))
System.out.println(c1)
