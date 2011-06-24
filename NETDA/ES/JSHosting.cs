using System;
using Microsoft.JScript;

public class JavaScriptEval
{
    public static Microsoft.JScript.Vsa.VsaEngine Engine = Microsoft.JScript.Vsa.VsaEngine.CreateEngine();

    public static object EvalJScript(string JScript)
    {
        object Result = null;
        try
        {
            Result = Microsoft.JScript.Eval.JScriptEvaluate(JScript, Engine);
        }
        catch (Exception ex)
        {
            return ex.Message;
        }
        return Result;
    }

    public static void Main()
    {
        // *** String Value
        object Result = EvalJScript(@"('hello world: ' + new Date())");
        Console.WriteLine( Result.ToString() + " - Type: " + Result.GetType().Name);

        // *** Return an integer or numeric - no conversion required
        Result = EvalJScript(@"( 11 * 12)");
        Console.WriteLine(Result.ToString() + " - Type: " + Result.GetType().Name);

        // *** Date value - must be converted!
        Result = EvalJScript(@"(new Date())");
        Console.WriteLine(Result + " - Type: " + Result.GetType().Name );

        // *** Must convert from DateObject to DateTime by coercing
        DateObject dateObject = Result as DateObject;
        Result = Microsoft.JScript.Convert.Coerce(Result,typeof(DateTime));
        Console.WriteLine("Converted to DateTime: "  + Result);

        // *** Block of code (last assignment is the return value)
        Result = EvalJScript(@"var out = 'hello';   for ( var x = 1; x < 10; x++) { out = out + 'Line ' + x + '\n'; }");
        Console.WriteLine(Result + " - Type: " + Result.GetType().Name);

        /// *** Closure - calling a JavaScript Function with return value
        Result = EvalJScript(@"( function Test(inputParm) {  return 'hello world ' + inputParm; } )");

        /// *** Invoke the function and retrieve the result
        Closure close = Result as Closure;

        // *** This requires full trust
        Result = close.Invoke(close, new object[1] { "Start with this bub..." });
        Console.WriteLine(Result);
        Console.WriteLine(" - Type: " + Result.GetType().Name);

        // *** JSON style object
        Result = EvalJScript(@"( {""timeString"":'Time is: ' + new Date(),""dateValue"":new Date()} )");
        JSObject obj = Result as JSObject;
        Console.WriteLine(Result);

        // *** JavaScript style property array access can be used
        object val = obj["dateValue"];
        Console.WriteLine(Microsoft.JScript.Convert.Coerce(val,typeof(DateTime)));

        val = obj["timeString"];
        Console.WriteLine(val);        
    }       
}