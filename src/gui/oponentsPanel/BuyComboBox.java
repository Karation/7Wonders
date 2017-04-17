package gui.oponentsPanel;

import effects.Effect;
import effects.GoodEffect;
import effects.MixedResourceEffect;
import effects.ResourceEffect;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.DefaultComboBoxModel;

public class BuyComboBox extends JComboBox{
    ImageIcon[] images;
    String[] effectStrings ;
    DefaultComboBoxModel model;
    public BuyComboBox(ArrayList<Effect> effects){

        effectStrings= new String[effects.size()];
        for (int i = 0; i < effects.size(); i++) {
            if (effects.get(i) instanceof ResourceEffect)
                effectStrings[i]=((ResourceEffect) effects.get(i)).getResourceType();
            else if(effects.get(i) instanceof GoodEffect)
                effectStrings[i]=((GoodEffect) effects.get(i)).getGoodType();
            else if(effects.get(i) instanceof MixedResourceEffect)
                effectStrings[i]=((MixedResourceEffect) effects.get(i)).getEffectName();
        }
        images = new ImageIcon[effectStrings.length];
        for (int i = 0; i < effectStrings.length; i++) {
            images[i] = new ImageIcon("Icons\\" + effectStrings[i] + ".jpg");
        }
            this.clear();
            this.setSelectedItem(0);
                model = populate(images, effectStrings, effects);
                this.setModel(model);
                this.setRenderer(new BuyComboBoxRenderer());



    }
    private void clear(){
        model=new DefaultComboBoxModel();
        this.setModel(model);
    }
    private DefaultComboBoxModel populate(ImageIcon[] images, String[] effectStrings, ArrayList<Effect> resourceOrGoodEffects){
        model = new DefaultComboBoxModel();
            for (int i = 0; i < effectStrings.length; i++) {
                model.addElement(new ImagesAndText(images[i], effectStrings[i], resourceOrGoodEffects.get(i)));
            }
        return model;
    }
    public void removeElement(int index){
        model.removeElementAt(index);
        this.paintAll(this.getGraphics());
    }

}
class BuyComboBoxRenderer extends JLabel implements ListCellRenderer{

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        ImagesAndText icon=(ImagesAndText) value;

        if (icon!=null) {
            setText(icon.getResourceName());
            setIcon(icon.getIcon());
        }else{
            setIcon(null);
            setText("Cant buy");
        }
        if(isSelected){
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }else{
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        setFont(list.getFont());

        return this;
    }
}
class ImagesAndText{
    private ImageIcon icon;
    private String resourceName;
    private Effect resourceOrGoodEffect;

    public ImagesAndText(ImageIcon icon, String resourceName, Effect resourceOrGoodEffect) {
        this.icon = icon;
        this.resourceName = resourceName;
        this.resourceOrGoodEffect=resourceOrGoodEffect;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Effect getResourceOrGoodEffect() {
        return resourceOrGoodEffect;
    }

    public void setResourceOrGoodEffect(Effect resourceOrGoodEffect) {
        this.resourceOrGoodEffect = resourceOrGoodEffect;
    }
}