
/**
  * MidiJS
  *
  * @author Aluizio Arcela
  * @version 1.0, 06/09/2001
  */

import javax.sound.midi.*;

public class MidiJS extends Midi
{
    Synthesizer   sintetizador;
    Instrument    instrumentos[];
    DadosDeCanal  canais[];

	 Sequencer sequenciador = null;


    class DadosDeCanal
    { MidiChannel canalMIDI;

      public DadosDeCanal(MidiChannel canal)
      { this.canalMIDI = canal;
      }
    }

    public MidiJS()
    {
      try
      { sequenciador = MidiSystem.getSequencer();
		sequenciador.open();
	  }
	  catch (Exception e)
	  { System.out.println("erro de seqüenciador");
	  }

	  this.open();
    }

    public void note_on(int tec, int vel, int kan)
    { canais[kan].canalMIDI.noteOn(tec, vel);
    }

    public void note_off(int tec, int vel, int kan)
    { canais[kan].canalMIDI.noteOff(tec, vel);
    }

    public void programChange(int programa, int kan)
    { canais[kan].canalMIDI.programChange(programa);
    }

    public void controlChange(int controle, int valor, int kan)
    { canais[kan].canalMIDI.controlChange(controle, valor);
    }

    public void pan(int valor, int kan)
    { canais[kan].canalMIDI.controlChange(10, valor);
    }

    public void ataque(int valor, int kan)
    { canais[kan].canalMIDI.controlChange(73, valor);
    }

    public void bank_select_all(int banco)
    { for (int i=0; i<15; i++)
      this.bank_select(banco, i);
    }

    public void bank_select(int bancoMIDI, int kan)
    { canais[kan].canalMIDI.controlChange(0, bancoMIDI);
    }

    public void notes_off_canal(int kan)
    { canais[kan].canalMIDI.controlChange(123, 0);
    }

    public void notes_off_geral()
    { for(int i=0; i<canais.length; i++)
      this.notes_off_canal(i);
    }
    
    public void tocaSequencia(Sequence sequencia, int trilha) throws Exception
    { sequenciador.setSequence(sequencia);
      sequenciador.setTrackSolo(trilha, true);
      sequenciador.start();
    }
    
    public void stopSequencia() throws Exception
    { sequenciador.stop();
    }
    
    public void toca(int instru, int tec, int lsb, int msb, int p1, int p2, int p3, int p4, int ort, int kan)
    { int veloc  = p1+p2+p3+p4;
      int instr  = instru;
      int tecla  = tec;
      int orto   = ort;
      int kanal  = kan;
      programChange(instr, kanal);
      pan(127*orto, kanal);
      ataque(instru, kanal);
      note_on(tecla, veloc, kanal);
    }
    
    public void open()
    { try { if(sintetizador == null)
              { if ((sintetizador = MidiSystem.getSynthesizer()) == null)
                   { System.out.println("getSynthesizer() falhou!"); 
                     return;
                   }
              }
              sintetizador.open();
           }
    catch (Exception ex) { ex.printStackTrace(); return; }
    Soundbank sb = sintetizador.getDefaultSoundbank();
    if (sb != null)
    { instrumentos = sintetizador.getDefaultSoundbank().getInstruments();
      sintetizador.loadInstrument(instrumentos[0]);
    }
    MidiChannel canaisMidi[] = sintetizador.getChannels();
    canais = new DadosDeCanal[canaisMidi.length];
    for(int i=0; i<canais.length; i++)
    { canais[i]= new DadosDeCanal(canaisMidi[i]); }
    }
    
    public void close()
    { if (sintetizador != null) { sintetizador.close();}
      sintetizador = null;
      instrumentos = null;
      canais       = null;
    }

}    //-----fim da classe MidiJS