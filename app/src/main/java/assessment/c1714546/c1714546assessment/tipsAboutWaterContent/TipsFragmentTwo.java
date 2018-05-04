package assessment.c1714546.c1714546assessment.tipsAboutWaterContent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import assessment.c1714546.c1714546assessment.R;

/**
 * This class is a fragment which is inflated into it's
 * parents activity's layout programatically. It includes
 * the ability for the user to send an email via an
 * implicit Intent.
 *
 * Created by c1714546 on 5/3/2018.
 *
 * @author Abbey Ross, 04/04/2018.
 * @version 1.0.
 */

public class TipsFragmentTwo extends Fragment implements View.OnClickListener, View.OnLongClickListener {
    private AppCompatEditText emailEditText;
    private AppCompatEditText messageEditText;
    private AppCompatEditText phoneNumberEditText;

    public TipsFragmentTwo() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tips_fragment_two_layout, container, false);

        // Grabbing edittexts to be able to retrieve user's input.
        this.emailEditText = (AppCompatEditText)v.findViewById(R.id.email_content);
        this.emailEditText.setOnClickListener(this);
        this.messageEditText = (AppCompatEditText)v.findViewById(R.id.message_content);
        this.messageEditText.setOnClickListener(this);
        this.phoneNumberEditText = (AppCompatEditText)v.findViewById(R.id.phone_number);
        this.phoneNumberEditText.setOnClickListener(this);

        // Accessing buttons and setting onClickListeners.
        AppCompatButton sendEmailBtn = (AppCompatButton)v.findViewById(R.id.send_email_btn);
        sendEmailBtn.setOnClickListener(this);
        AppCompatButton resetBtn = (AppCompatButton)v.findViewById(R.id.reset_btn);
        resetBtn.setOnLongClickListener(this);
        AppCompatButton callPhoneBtn = (AppCompatButton)v.findViewById(R.id.call_btn);
        callPhoneBtn.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        // This method clears an EditText of writing
        // should the user click on it.
        int viewComponentId = v.getId();

        switch (viewComponentId) {
            case R.id.email_content :
                this.emailEditText.setText("");
                break;
            case R.id.message_content :
                this.messageEditText.setText("");
                break;
            case R.id.send_email_btn :
                sendAnEmail();
                break;
            case R.id.phone_number :
                this.phoneNumberEditText.setText("");
                break;
            case R.id.call_btn :
                callPhone();
                break;
            default :
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        // This method clears all EditTexts of
        // writing as a Reset feature.
        this.emailEditText.setText("");
        this.messageEditText.setText("");
        return false;
    }

    public void sendAnEmail() {
        //Gather Email Address.
        String emailAddress = this.emailEditText.getText().toString();
        if (emailAddress != "") {

            //If an email address has been gathered, check for message content.
            String text = this.messageEditText.getText().toString();
            if (text != "") {
                //Message has been gathered, now create the intent.
                Intent anEmailIntent = new Intent(Intent.ACTION_SEND);

                //Define the basic data of the intent.
                anEmailIntent.setData(Uri.parse("mailto:"));
                anEmailIntent.setType("text/plain");

                //Express to Who
                anEmailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddress);

                //Express the Subject
                anEmailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_water_life_app));

                //Place the message content
                anEmailIntent.putExtra(Intent.EXTRA_TEXT, text);

                //Try acting on the Intent
                try {
                    startActivity(Intent.createChooser(anEmailIntent, "Send email.."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), R.string.no_email_client, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), R.string.enter_a_msg_warning, Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(getActivity(), R.string.specify_email_warning, Toast.LENGTH_SHORT).show();
        }
    }

    public void callPhone() {
        // Get Phone Number
        String number = this.phoneNumberEditText.getText().toString();
        // Remove any spaces in it
        String phone = getRidOfSpaces(number);
        //Create call Intent
        Intent aCallIntent = new Intent(Intent.ACTION_DIAL);
        //Pass data to it
        aCallIntent.setData(Uri.parse("tel:"+phone));
        startActivity(aCallIntent);
    }

    public String getRidOfSpaces(String num) {
        String value = "";

        for(Character digit : num.toCharArray()) {
            if(Character.isWhitespace(digit) == false)
                value = value + digit;
        }
        return value;
    }

}
