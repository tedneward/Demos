import org.codehaus.groovy.scriptom.*
import static org.codehaus.groovy.scriptom.tlb.sapi.SpeechVoiceSpeakFlags.*
import static org.codehaus.groovy.scriptom.tlb.sapi.SpeechRunState.*

//def voice = new ActiveXObject('SAPI.SpVoice')
//voice.speak "Hello, GROOVY world!"


Scriptom.inApartment
{
  def scriptControl = new ActiveXObject("ScriptControl")
  scriptControl.Language = "JScript"
  println scriptControl.Eval('2.0 + 2.0;')
}
